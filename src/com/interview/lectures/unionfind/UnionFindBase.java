package com.interview.lectures.unionfind;

public abstract class UnionFindBase implements IUnionFind {

  public final int N;
  public int[] index;

  public UnionFindBase(int N) {
    this.N = N;
    index = new int[N];
    for (int i = 0; i < N; i++) {
      index[i] = i;
    }
  }

  public boolean checkIndex(int index) {
    return index >= 0 && index < N;
  }

  public String toString() {
    StringBuilder builder = new StringBuilder();
    for (int i = 0; i < N; i++) {
      builder.append(index[i]);
      builder.append(" ");
    }
    return builder.toString();
  }
}
