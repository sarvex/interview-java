package com.interview.algorithms.tree;

import com.interview.datastructures.tree.BinarySearchTree;
import com.interview.datastructures.tree.BinaryTreeNode;

import java.util.ArrayList;
import java.util.List;

public class TreeTraverse {

  /**
   * @param args
   */
  public static void main(String[] args) {
    System.out.println("The Tree Traverse Implementation");
    System.out.println("========================================================================");

    System.out.println("Tree Structure : \n--------");
    System.out.println("            6");
    System.out.println("           / \\ ");
    System.out.println("          4   8");
    System.out.println("         / \\ / \\ ");
    System.out.println("        3  5 7  9");

//		ConsoleReader reader = new ConsoleReader();
//		System.out.print("Please input a list of tree node values: ");
    int[] array = {6, 4, 8, 3, 5, 7, 9};
    BinarySearchTree tree = new BinarySearchTree(array);

    TreeTraverse runner = new TreeTraverse();

    System.out.println("\nTree Traversal Results : \n--------");
    System.out.print("\tPreOrderTraversal: ");
    runner.traverseByPreOrder(tree.getRoot());

    System.out.print("\n\tInOrderTraversal: ");
    runner.traverseByInOrder(tree.getRoot());

    System.out.print("\n\tPostOrderTraversal: ");
    runner.traverseByPostOrder(tree.getRoot());

    System.out.print("\n\tBreadth First Traversal: ");
    runner.traverseInBreadthFirstOrder(tree.getRoot());

    System.out.print("\n\tDepth First Traversal: ");
    runner.traversInDepthFirstOrder(tree.getRoot());

  }

  public void traversInDepthFirstOrder(BinaryTreeNode tree) {
    if (tree == null)
      return;

    System.out.print(tree.getValue() + " ");
    this.traversInDepthFirstOrder(tree.getLeftChild());
    this.traversInDepthFirstOrder(tree.getRightChild());
  }

  public void traverseInBreadthFirstOrder(BinaryTreeNode tree) {
    if (tree == null)
      return;

    List<BinaryTreeNode> pendings = new ArrayList<BinaryTreeNode>();
    pendings.add(tree);

    while (pendings.size() > 0) {
      BinaryTreeNode node = pendings.remove(0);
      System.out.print(node.getValue() + " ");
      // add children to pendings
      if (node.getLeftChild() != null)
        pendings.add(node.getLeftChild());
      if (node.getRightChild() != null)
        pendings.add(node.getRightChild());
    }

  }

  public void traverseByPreOrder(BinaryTreeNode tree) {
    if (tree == null)
      return;

    System.out.print(tree.getValue() + " ");
    this.traverseByPreOrder(tree.getLeftChild());
    this.traverseByPreOrder(tree.getRightChild());
  }

  public void traverseByInOrder(BinaryTreeNode tree) {
    if (tree == null)
      return;

    this.traverseByInOrder(tree.getLeftChild());
    System.out.print(tree.getValue() + " ");
    this.traverseByInOrder(tree.getRightChild());
  }

  public void traverseByPostOrder(BinaryTreeNode tree) {
    if (tree == null)
      return;
    this.traverseByPostOrder(tree.getLeftChild());
    this.traverseByPostOrder(tree.getRightChild());
    System.out.print(tree.getValue() + " ");
  }

}
