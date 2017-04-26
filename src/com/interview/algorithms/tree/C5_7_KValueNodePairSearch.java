package com.interview.algorithms.tree;

import com.interview.datastructures.tree.BinarySearchTree;
import com.interview.datastructures.tree.BinaryTreeNode;
import com.interview.utils.ConsoleReader;

class NodePair {

  private BinaryTreeNode firstNode;
  private BinaryTreeNode secondNode;

  public BinaryTreeNode getFirstNode() {
    return firstNode;
  }

  public void setFirstNode(BinaryTreeNode node) {
    firstNode = node;
  }

  public BinaryTreeNode getSecondNode() {
    return secondNode;
  }

  public void setSecondNode(BinaryTreeNode node) {
    secondNode = node;
  }

}

/**
 * KValue Node Pair search implementation. Find the pairs of nodes where the values of the nodes in each pair sum to
 * a given value N
 *
 * @author zouzhile (zouzhile@gmail.com)
 */
public class C5_7_KValueNodePairSearch {

  public static void main(String[] args) {
    System.out.println("The K Value Node Pair Search Implementation");
    System.out.println("========================================================================");

    //Prepare sorted input
    ConsoleReader reader = new ConsoleReader();
    System.out.print("Please input a list of tree node values: ");
    int[] array = reader.readIntItems();
    BinarySearchTree tree = new BinarySearchTree(array);

    System.out.print("Please input sum value for the node pair: ");
    int target = reader.readInt();
    System.out.println();

    C5_7_KValueNodePairSearch searcher = new C5_7_KValueNodePairSearch();
    NodePair pair = searcher.search(tree.getRoot(), target);
    if (pair != null)
      System.out.println("K Value Node Pair Found: " + pair.getFirstNode().getValue() + "\t" + pair.getSecondNode()
          .getValue());
    else
      System.out.println("K Value Node Pair NOT Found !");
  }

  public NodePair search(BinaryTreeNode currentNode, int sumValue) {
    NodePair pair = null;
    if (currentNode != null) {
      int value = currentNode.getValue();

      BinaryTreeNode matchNode = this.find(currentNode.getLeftChild(), sumValue - value);
      if (matchNode == null)
        matchNode = this.find(currentNode.getRightChild(), sumValue - value);

      if (matchNode != null) {
        pair = new NodePair();
        pair.setFirstNode(currentNode);
        pair.setSecondNode(matchNode);
      } else {
        pair = this.search(currentNode.getLeftChild(), sumValue);
        if (pair == null)
          pair = this.search(currentNode.getRightChild(), sumValue);
      }
    }
    return pair;
  }

  private BinaryTreeNode find(BinaryTreeNode node, int targetNodeValue) {
    if (node == null)
      return null;
    // set node to the root of the binary tree
    while (node.getParent() != null)
      node = node.getParent();

    // use binary search to find the node with the given value
    BinaryTreeNode target = node;
    while (target != null) {
      int value = target.getValue();
      if (value == targetNodeValue)
        break;
      if (targetNodeValue < value)
        target = target.getLeftChild();
      else
        target = target.getRightChild();
    }
    return target;
  }
}
