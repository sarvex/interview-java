package com.interview.algorithms.list;

public class Node implements Comparable<Node> {
  private final String value;
  private Node nextNode;

  public Node(String value, Node nextNode) {
    this.value = value;
    this.nextNode = nextNode;
  }

  public static Node createList(int[] items) {
    int length = items.length;
    Node head = null;

    for (int index = length - 1; index >= 0; index--) {
      Node node = new Node("" + items[index], head);
      head = node;
    }

    return head;
  }

  public String getValue() {
    return value;
  }

  public Node next() {
    return nextNode;
  }

  public void setNextNode(Node nextNode) {
    this.nextNode = nextNode;
  }

  @Override
  public int compareTo(Node target) {
    try {
      int sourceValue = Integer.parseInt(value);
      int targetValue = Integer.parseInt(target.getValue());
      return sourceValue - targetValue;
    } catch (Exception e) {
      // values are not numbers, do nothing here
    }
    return value.compareTo(target.getValue());
  }

}
