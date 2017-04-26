package com.interview.datastructures.tree;

public class Trie {

  private final TrieNode root;

  public Trie() {
    root = new TrieNode();
  }

  public void addWord(String word) {
    TrieNode node = root;
    for (char c : word.toCharArray()) {
      node = node.addChild(c);
      if (node == null)
        return;
    }
    node.setWord(true);
  }

  public boolean isWord(String s) {
    TrieNode node = root;
    for (char c : s.toCharArray()) {
      node = node.get(c);
      if (node == null)
        return false;
    }
    return node.isWord();
  }

  public TrieNode match(String s) {
    TrieNode node = root;
    for (char c : s.toCharArray()) {
      node = node.get(c);
      if (node == null)
        return null;
    }

    return node;
  }

}
