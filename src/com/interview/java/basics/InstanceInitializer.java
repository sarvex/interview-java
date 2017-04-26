package com.interview.java.basics;

public class InstanceInitializer {

  private final int a = 10; // instance variable initializer
  private final int c;
  //private int b = c;  <- This causes compiling error
  private final int b = this.initB(); // b is set to 0, since c is not initialized yet.

  {
    c = 20; // this code block is called instance initializer
  }

  public static void main(String[] args) {
    InstanceInitializer init = new InstanceInitializer();
    System.out.println("B is initialized as: " + init.getB());
    System.out.println("C is initialized as: " + init.getC());
  }

  private int initB() {
    return 100 * c;
  }

  public int getA() {
    return a;
  }

  public int getB() {
    return b;
  }

  public int getC() {
    return c;
  }

}
