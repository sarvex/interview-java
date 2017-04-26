package com.interview.datastructures.hashtable;

public class SimpleHashTable<K, V> {

  private float loadFactor;
  private int capacity;
  private int count;
  private int threshold;
  private transient SimpleHashTable.Entry<K, V>[] table;

  @SuppressWarnings("unchecked")
  public SimpleHashTable(int initialCapacity, float loadFactor) {
    if (initialCapacity < 0 || loadFactor <= 0 || Float.isNaN(loadFactor))
      throw new IllegalArgumentException(String.format("Illegal capacity or loadFactor [capacity=%s,loadFactor=%s]",
          initialCapacity, loadFactor));
    capacity = initialCapacity;
    this.loadFactor = loadFactor;
    table = (SimpleHashTable.Entry<K, V>[]) new SimpleHashTable.Entry[capacity];
    threshold = (int) (capacity * this.loadFactor);
  }

  public SimpleHashTable() {
    this(100000, 0.75f);
  }

  public static void main(String[] args) {
    SimpleHashTable<String, String> table = new SimpleHashTable<String, String>(3, 0.75f);

    table.put("a", "a_value");
    table.put("b", "b_value");
    table.put("c", "c_value");
    table.put("d", "d_value");
    table.put("e", "e_value");
    table.put("f", "f_value");
    table.put("g", "g_value");

    System.out.println(table.get("a"));
    System.out.println(table.get("b"));
    System.out.println(table.get("c"));
    System.out.println(table.get("d"));
    System.out.println(table.get("e"));
    System.out.println(table.get("f"));
    System.out.println(table.get("g"));

    System.out.println(table.get("ooo"));
  }

  public synchronized V get(K key) {
    int hash = key.hashCode();
    int index = (hash & 0x7FFFFFFF) % capacity;

    for (SimpleHashTable.Entry<K, V> entry = table[index]; entry != null; entry = entry.next) {
      if (entry.hash == hash && entry.key.equals(key)) {
        return entry.value;
      }
    }
    return null;
  }

  public synchronized V put(K key, V value) {
    if (key == null || value == null)
      throw new IllegalArgumentException("Null is not allowed for key or value");
    int hash = key.hashCode();
    int index = (hash & 0x7FFFFFFF) % capacity;

    for (SimpleHashTable.Entry<K, V> entry = table[index]; entry != null; entry = entry.next) {
      if (entry.hash == hash && entry.key.equals(key)) {
        V oldValue = entry.value;
        entry.value = value;
        return oldValue;
      }
    }

    if (count >= threshold) {
      rehash();
      index = (hash & 0x7FFFFFFF) % capacity;
    }

    SimpleHashTable.Entry<K, V> entry = table[index];
    table[index] = new SimpleHashTable.Entry<K, V>(hash, key, value, entry);
    count++;
    return null;
  }

  @SuppressWarnings("unchecked")
  private void rehash() {
    int newCapacity = capacity * 2 + 1;
    SimpleHashTable.Entry<K, V>[] newTable = (SimpleHashTable.Entry<K, V>[]) new SimpleHashTable.Entry[newCapacity];

    for (int i = capacity - 1; i >= 0; i--) {
      for (SimpleHashTable.Entry<K, V> entry = table[i]; entry != null; ) {
        SimpleHashTable.Entry<K, V> nextEntry = entry.next;
        int index = (entry.hash & 0x7FFFFFFF) % newCapacity;
        entry.next = newTable[index];
        newTable[index] = entry;
        entry = nextEntry;
      }
    }

    capacity = newCapacity;
    table = newTable;
    threshold = (int) (capacity * loadFactor);
  }

  public synchronized V remove(K key) {
    int hash = key.hashCode();
    int index = (hash & 0x7FFFFFFF) % capacity;

    SimpleHashTable.Entry<K, V> previous = null;

    for (SimpleHashTable.Entry<K, V> entry = table[index]; entry != null; previous = entry, entry = entry.next) {
      if (entry.hash == hash && entry.key.equals(key)) {
        V value = entry.value;
        if (previous == null) {
          table[index] = entry.next;
        } else {
          previous.next = entry.next;
        }
        count--;
        entry = null;
        return value;
      }
    }
    return null;
  }

  @SuppressWarnings("hiding")
  class Entry<K, V> {
    public int hash;
    public K key;
    public V value;
    public SimpleHashTable.Entry<K, V> next;

    public Entry(int hash, K key, V value, SimpleHashTable.Entry<K, V> next) {
      this.hash = hash;
      this.key = key;
      this.value = value;
      this.next = next;
    }
  }
}
