package com.interview.lectures.unionfind;

public class EnhancedQuickUnionWeightTree extends UnionFindBase {
  /**
   * enhance quick union by weight tree in order to avoid tailer tree
   * union operation: weight the subtree, put the small tree under the big tree to avoid tail tree
   * assign the parent of big tree to the parent of small tree
   * find operation: in one connectivity if the parent of a and b is same
   * quickunion:
   * initialize: N
   * union: lgN
   * find: lgN
   */

  public int[] treesize;

  public EnhancedQuickUnionWeightTree(int N) {
    super(N);
    treesize = new int[N];
    for (int i = 0; i < N; i++) {
      treesize[i] = 1;
    }
  }

  @Override
  public void union(int a, int b) {
    int parent_a = getParent(a);
    int parent_b = getParent(b);
    if (treesize[parent_a] >= treesize[parent_b]) {
      index[parent_b] = parent_a;
      treesize[parent_a] += treesize[parent_b];
    } else {
      index[parent_a] = parent_b;
      treesize[parent_b] += treesize[parent_a];
    }

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

  public boolean checkIndex(int index) {
    return index >= 0 && index < N;
  }

  @Override
  public int find(int a) {
    // TODO Auto-generated method stub
    return 0;
  }

}
