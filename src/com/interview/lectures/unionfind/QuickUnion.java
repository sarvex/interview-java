package com.interview.lectures.unionfind;

public class QuickUnion extends UnionFindBase {
  /**
   * Use a integer array to store the forest created by connectivity.
   * the value in index[i] is the parent of i;
   * union operation: assign the parent of a to the parent of b
   * find operation: in one connectivity if the parent of a and b is same
   * quickunion:
   * initialize: N
   * union: N
   * find: N
   */

  public QuickUnion(int N) {
    super(N);
  }

  @Override
  public void union(int a, int b) {
    int parent_a = getParent(a);
    int parent_b = getParent(b);
    index[parent_a] = parent_b;
  }

  @Override
  public boolean connected(int a, int b) {
    return getParent(a) == getParent(b);
  }

  public int getParent(int index) {
    while (this.index[index] != index) {
      index = this.index[index];
    }
    return index;
  }

  @Override
  public int find(int a) {
    int parent_a = getParent(a);
    for (int i = N - 1; i >= 0; i--) {
      if (getParent(i) == parent_a) {
        return i;
      }
    }
    return a;
  }
}
