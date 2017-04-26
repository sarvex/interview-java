package com.interview.datastructures.tree;

public class TrieNode {

  private final TrieNode[] children;
  private boolean isWord;

  public TrieNode() {
    children = new TrieNode[26];
  }

  public TrieNode addChild(char child) {
    if (child < 'a' || child > 'z')
      return null;

    int offset = child - 'a';
    if (children[offset] == null) {
      children[offset] = new TrieNode();
    }
    return children[offset];
  }

  public boolean isWord() {
    return isWord;
  }

  public void setWord(boolean isWord) {
    this.isWord = isWord;
  }

  public TrieNode get(char c) {
    int offset = c - 'a';
    return children[offset];
  }

}
