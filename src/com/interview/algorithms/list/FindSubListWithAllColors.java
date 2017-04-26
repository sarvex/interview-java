package com.interview.algorithms.list;

import java.util.HashMap;

public class FindSubListWithAllColors {

  /**
   * @param args
   */
  public static void main(String[] args) {
    System.out.println("a".split(",").length);

  }

  /**
   * @param head         A cyclic list contains M nodes with N types of colors
   * @param colorOffsets
   * @return
   */
  public FindSubListWithAllColors.State find(Node head, int colorCount) {
    FindSubListWithAllColors.State state = new FindSubListWithAllColors.State(head);

    while (!state.isCompleted()) {
      if (state.colors.size() == colorCount) {
        int length = state.stepsFromHeadToEnd - state.stepsFromHeadToStart;
        if (length < state.maxSubListLength) {
          state.maxSubListStart = state.start;
          state.maxSubListEnd = state.end;
          state.maxSubListLength = length;
        }

        state.colors.remove(state.start.getValue());
        state.start = state.start.next();
        state.stepsFromHeadToStart++;
        compactSubList(state);
      } else if (state.start.getValue().equals(state.end.getValue())) // there is always 1 node with the color of
        // start node in current sublist
        compactSubList(state);
      else {
        state.end = state.end.next();
        state.updateColorsForEndNode();
      }
    }
    return state;
  }

  /*
   * Make the start->end sublist the smallest and contains all current colors.
   * The algorithm is to move start node to the node with its color only appeared once and its
   *  stepsFromHeadToStart is the smallest
   */
  private void compactSubList(FindSubListWithAllColors.State state) {
    while (state.colors.get(state.start.getValue()).split(",").length > 1) {
      String[] startNodeColors = state.colors.get(state.start.getValue()).split(",");
      String colorsString = startNodeColors[1];
      for (int i = 2; i < startNodeColors.length; i++) {
        colorsString += "," + startNodeColors[i];
      }
      state.colors.put(state.start.getValue(), colorsString);
      state.start = state.start.next();
      state.stepsFromHeadToStart++;
    }
  }

  class SubList {
    Node start, end;
    int length;

    public SubList(Node start, Node end, int length) {
      this.start = start;
      this.end = end;
      this.length = length;
    }
  }

  class State {
    int stepsFromHeadToEnd = 1, stepsFromHeadToStart, maxSubListLength = Integer.MAX_VALUE;
    Node start, end, head;
    Node maxSubListStart, maxSubListEnd;
    HashMap<String, String> colors = new HashMap<String, String>();

    State(Node head) {
      this.head = head;
      start = end = head;
      this.updateColorsForEndNode();
      end = start.next();
      this.updateColorsForEndNode();
      maxSubListStart = start;
      maxSubListEnd = end;
      maxSubListLength = this.getCurrentSubListLength();
    }

    public boolean isCompleted() {
      boolean completed = (stepsFromHeadToStart == 0 && end.next() == head) ||
          (stepsFromHeadToStart > 0 && start == head);
      return completed;
    }

    public int getCurrentSubListLength() {
      return stepsFromHeadToEnd - stepsFromHeadToStart;
    }

    public void updateColorsForEndNode() {
      String color = end.getValue();
      if (colors.get(color) == null)
        colors.put(color, "" + stepsFromHeadToEnd);
      else {
        String offsets = colors.get(color) + "," + stepsFromHeadToEnd;
        colors.put(color, offsets);
      }
    }
  }

}
