package com.interview.lectures.unionfind;

public interface IUnionFind {

  void union(int a, int b);

  boolean connected(int a, int b);

  int find(int a);

  String toString();
}
