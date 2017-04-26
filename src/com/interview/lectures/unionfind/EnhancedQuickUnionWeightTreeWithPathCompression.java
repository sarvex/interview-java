package com.interview.lectures.unionfind;

public class EnhancedQuickUnionWeightTreeWithPathCompression extends UnionFindBase {

  public int[] treesize;

  public EnhancedQuickUnionWeightTreeWithPathCompression(int N) {
    super(N);
    treesize = new int[N];
    for (int i = 0; i < N; i++) {
      treesize[i] = 1;
    }
  }

  @Override
  public void union(int a, int b) {
    int parent_a = this.getParent(a);
    int parent_b = this.getParent(b);
    if (treesize[parent_a] > treesize[parent_b]) {
      index[parent_b] = parent_a;
      treesize[parent_a] += treesize[parent_b];
    } else {
      index[parent_a] = parent_b;
      treesize[parent_b] += treesize[parent_a];
    }

  }

  @Override
  public boolean connected(int a, int b) {
    return this.getParent(a) == this.getParent(b);
  }

  public int getParent(int index) {
    while (this.index[index] != index) {
      this.index[index] = this.index[this.index[index]];
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
