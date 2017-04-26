package com.interview.lectures.stack;

public class LinkedStack<T> implements Stack<T> {

  private LinkedStack.Node head = null;
  private int size = 0;

  @Override
  public void push(T item) {
    LinkedStack.Node node = head;
    head = new LinkedStack.Node();
    head.node = item;
    head.next = node;
    size++;
  }

  @Override
  public T pop() {
    if (head != null) {
      T node = head.node;
      head = head.next;
      size--;
      return node;
    } else {
      return null;
    }
  }

  @Override
  public boolean isEmpty() {
    return head == null;
  }

  @Override
  public int size() {
    return size;
  }

  @Override
  public T peek() {
    return head.node;
  }

  class Node {
    T node;
    LinkedStack.Node next;
  }

}
