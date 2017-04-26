package com.interview.lectures.unionfind;

public class QuickFind extends UnionFindBase {

  /**
   * User a integer array to store the flag of each node, the node with same flag in one connectivity.
   * quickfind:
   * initialize: N
   * union: N
   * find: 1
   */

  public QuickFind(int N) {
    super(N);
  }

  @Override
  public void union(int a, int b) {
    if (!checkIndex(a) || !checkIndex(b)) {
      System.err.println("input index out of bound");
      return;
    }
    int flag_a = index[a];
    int flag_b = index[b];
    if (flag_a == flag_b) {
      return;
    } else {
      for (int i = 0; i < N; i++) {
        if (index[i] == flag_a) {
          index[i] = flag_b;
        }
      }
    }
  }

  @Override
  public boolean connected(int a, int b) {
    if (!checkIndex(a) || !checkIndex(b)) {
      System.err.println("input index out of bound");
      return false;
    }
    return index[a] == index[b];
  }

  @Override
  public int find(int a) {
    int flag_a = index[a];
    for (int i = N - 1; i >= 0; i--) {
      if (index[i] == flag_a) {
        return i;
      }
    }
    return a;
  }

}
