package com.interview.datastructures.tree;

public class BinarySearchTree {

  private BinaryTreeNode root;

  public BinarySearchTree(int[] nodeValues) {
    root = new BinaryTreeNode(nodeValues[0]);
    for (int i = 1; i < nodeValues.length; i++) {
      this.insert(nodeValues[i]);
    }
  }

  public BinaryTreeNode getRoot() {
    return root;
  }

  public void insert(int newValue) {
    BinaryTreeNode node = root;
    boolean stop = false;
    while (!stop) {
      if (newValue <= node.getValue()) {
        if (node.getLeftChild() == null) {
          BinaryTreeNode newNode = new BinaryTreeNode(newValue);
          node.setLeftChild(newNode);
          newNode.setParent(node);
          stop = true;
        } else {
          node = node.getLeftChild();
        }
      } else {
        if (node.getRightChild() == null) {
          BinaryTreeNode newNode = new BinaryTreeNode(newValue);
          node.setRightChild(new BinaryTreeNode(newValue));
          newNode.setParent(node);
          stop = true;
        } else {
          node = node.getRightChild();
        }
      }
    }
  }

  public void delete(BinaryTreeNode node) {
    if (node == null) {
      return;
    }
    BinaryTreeNode parent = node.getParent();

    if (node.getLeftChild() == null && node.getRightChild() == null) {
      // The given node is a leaf node
      if (parent == null) {
        root = null;
        return;
      }
      if (node == parent.getLeftChild()) {
        parent.setLeftChild(null);
      } else if (node == parent.getRightChild()) {
        parent.setRightChild(null);
      }
    } else if (node.getLeftChild() == null && !(node.getRightChild() == null)) {
      // the given node only has right child
      BinaryTreeNode rightChild = node.getRightChild();
      node.setValue(rightChild.getValue());
      delete(rightChild);
    } else if (!(node.getLeftChild() == null) && node.getRightChild() == null) {
      // the given node only has left child
      BinaryTreeNode leftChild = node.getLeftChild();
      node.setValue(leftChild.getValue());
      delete(leftChild);
    } else {
      // the given node has both left and right child
      // replace node with its direct successor
      BinaryTreeNode successor = node.getRightChild();
      while (successor.getLeftChild() != null) {
        successor = successor.getLeftChild();
      }
      node.setValue(successor.getValue());
      delete(successor);
    }
  }

  public BinaryTreeNode search(int value) {
    BinaryTreeNode node = root;
    while (node != null) {
      if (node.getValue() == value) {
        break;
      } else if (node.getValue() < value) {
        node = node.getLeftChild();
      } else {
        node = node.getRightChild();
      }
    }
    return node;
  }

}
