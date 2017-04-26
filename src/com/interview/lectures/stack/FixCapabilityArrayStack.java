package com.interview.lectures.stack;

public class FixCapabilityArrayStack<T> implements Stack<T> {

  private final T[] array;
  private final int N;
  private int current;

  @SuppressWarnings("unchecked")
  public FixCapabilityArrayStack(int N) {
    this.N = N;
    array = (T[]) new Object[N];
  }

  @Override
  public void push(T item) {
    if (size() < N) {
      array[current++] = item;
    } else {
      System.err.println("Stack is full");
    }

  }

  @Override
  public T pop() {
    if (!isEmpty()) {
      return array[--current];
    } else {
      return null;
    }
  }

  @Override
  public boolean isEmpty() {
    return current == 0;
  }

  @Override
  public int size() {
    return current - 1;
  }

  @Override
  public T peek() {
    return array[current];
  }

}
