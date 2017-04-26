package com.interview.datastructures.tree;

public class BinaryTreeNode {

  private int value;
  private BinaryTreeNode leftChild;
  private BinaryTreeNode rightChild;
  private BinaryTreeNode parent;

  public BinaryTreeNode(int value) {
    this.value = value;
  }

  public BinaryTreeNode getLeftChild() {
    return leftChild;
  }

  public void setLeftChild(BinaryTreeNode leftChild) {
    this.leftChild = leftChild;
    leftChild.setParent(this);
  }

  public BinaryTreeNode getRightChild() {
    return rightChild;
  }

  public void setRightChild(BinaryTreeNode rightChild) {
    this.rightChild = rightChild;
    rightChild.setParent(this);
  }

  public int getValue() {
    return value;
  }

  public void setValue(int value) {
    this.value = value;
  }

  public BinaryTreeNode getParent() {
    return parent;
  }

  public void setParent(BinaryTreeNode parent) {
    this.parent = parent;
  }

}
