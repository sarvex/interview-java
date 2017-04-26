package com.interview.datastructures.stack;

/**
 * Design a stack. We want to push, pop, and also, retrieve the minimum element in constant time.
 *
 * @author zouzhile
 */
public class SimpleStack<T extends Comparable> {

  private SimpleStack.Node head;
  private SimpleStack.Node minHead;

  public SimpleStack() {

  }

  /**
   * @param args
   */
  public static void main(String[] args) {
    // TODO Auto-generated method stub

  }

  public void push(T value) {
    SimpleStack.Node node = new SimpleStack.Node();
    node.value = value;

    if (head == null) {
      head = node;
      SimpleStack.Node clone = node.clone();
      minHead = clone;
    } else {
      SimpleStack.Node clone = null;
      if (node.value.compareTo(minHead.value) < 0)
        clone = node.clone();
      else
        clone = minHead.clone();
      // data stack
      node.next = head;
      head = node;
      // min stack
      clone.next = minHead;
      minHead = clone;
    }
  }

  public T pop() {
    T result = null;

    if (head != null) {
      // min stack
      SimpleStack.Node temp = minHead;
      minHead = minHead.next;
      temp.next = null;

      // data stack
      temp = head;
      result = head.value;
      head = head.next;
      temp.next = null;
    }

    return result;
  }

  public T getMinValue() {
    T min = null;
    if (minHead != null)
      min = minHead.value;
    return min;
  }

  class Node {
    T value;
    SimpleStack.Node next, previous;

    protected SimpleStack.Node clone() {
      SimpleStack.Node clone = new SimpleStack.Node();
      clone.value = this.value;
      clone.next = this.next;
      clone.previous = this.previous;
      return clone;
    }
  }

}
