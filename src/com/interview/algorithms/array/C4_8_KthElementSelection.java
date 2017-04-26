package com.interview.algorithms.array;

import com.interview.utils.ConsoleReader;

/**
 * This implements the "Median of median" selection algorithm to find the Kth small element in an array.
 * The time complexity is O(n). Details can be found in page 112 of Introduction to Algorithms book 2nd edition
 * .
 *
 * @author zouzhile
 */
public class C4_8_KthElementSelection {

  private static final int GROUP_SIZE = 5;

  /**
   * @param args
   */
  public static void main(String[] args) {
    C4_8_KthElementSelection selector = new C4_8_KthElementSelection();
    ConsoleReader reader = new ConsoleReader();
    //int[] data = new int[]{ 2, 3, 5, 1, 9, 10, -22};
    System.out.print("Please input array elements: ");
    int[] data = reader.readIntItems();
    System.out.print("Please input K: ");
    int K = reader.readInt();
    int offset = selector.select(data, 0, data.length - 1, K);
    System.out.println("The Kth element: " + data[offset]);
  }

  /**
   * Return the offset of the Kth smallest element in the sub array of "data" bounded by begin and end.
   * The data array is changed by element switching during selection.
   *
   * @param data
   * @param begin
   * @param end
   * @param K
   * @return
   */
  public int select(int[] data, int begin, int end, int K) {

    if (end - begin == 0)
      return begin;
    else if ((end - begin) == 1 && K == 1)
      return begin;
    else if ((end - begin) == 1 && K == 2)
      return end;

    int selectionOffset = begin;

    int medianGroupAmount = (end - begin + 1) % C4_8_KthElementSelection.GROUP_SIZE == 0 ? (end - begin + 1) /
        C4_8_KthElementSelection
        .GROUP_SIZE : (end - begin + 1)
        / C4_8_KthElementSelection.GROUP_SIZE + 1;
    int[] medians = new int[medianGroupAmount];
    int count = 0;

    // find the median of all groups
    for (int groupBeginIndex = begin; groupBeginIndex <= end; groupBeginIndex += C4_8_KthElementSelection.GROUP_SIZE) {
      int groupEndIndex = end - groupBeginIndex >= C4_8_KthElementSelection.GROUP_SIZE ? groupBeginIndex +
          C4_8_KthElementSelection.GROUP_SIZE - 1 : end;
      medians[count] = getGroupMedian(data, groupBeginIndex, groupEndIndex);
      count++;
    }

    // find the median of medians
    int groupOfMedianOfMedians = select(medians, 0, medians.length - 1, medians.length / 2);
    int offsetMedianOfMedians = groupOfMedianOfMedians * C4_8_KthElementSelection.GROUP_SIZE + 2;
    for (int i = groupOfMedianOfMedians * C4_8_KthElementSelection.GROUP_SIZE; i < groupOfMedianOfMedians *
        C4_8_KthElementSelection.GROUP_SIZE + 5; i++)
      if (data[i] == medians[groupOfMedianOfMedians]) {
        offsetMedianOfMedians = i;
        break;
      }
    int offsetMedianOfMediansAfterPartition = partition(data, begin, end, offsetMedianOfMedians);
    int KthSmallestElementOffset = begin + K - 1;
    if (offsetMedianOfMediansAfterPartition == KthSmallestElementOffset)
      selectionOffset = offsetMedianOfMediansAfterPartition;
    else if (offsetMedianOfMediansAfterPartition < KthSmallestElementOffset)
      selectionOffset = select(data, offsetMedianOfMediansAfterPartition + 1, end, K -
          offsetMedianOfMediansAfterPartition - 1);
    else
      selectionOffset = select(data, begin, offsetMedianOfMediansAfterPartition - 1, K);
    return selectionOffset;
  }

  private int partition(int[] data, int begin, int end, int pivotOffset) {
    int value = data[pivotOffset];
    switchElements(data, pivotOffset, end);
    int i = begin - 1;

    for (int j = begin; j < end; j++) {
      if (data[j] <= value) {
        i++;
        switchElements(data, i, j);
      }
    }
    switchElements(data, i + 1, end);
    return i + 1;
  }

  // DO NOT change the elements of data by switching elements.
  private int getGroupMedian(int[] data, int groupBeginIndex, int groupEndIndex) {
    int groupSize = groupEndIndex - groupBeginIndex + 1;
    int groupMedianIndex = groupBeginIndex;
    if (groupSize >= 3) { // group size is greater or equal than 3
      int[] group = new int[groupSize];
      int[] groupOffsets = new int[groupSize];
      for (int i = groupBeginIndex; i <= groupEndIndex; i++) {
        group[i - groupBeginIndex] = data[i];
        groupOffsets[i - groupBeginIndex] = i;
      }
      for (int i = 0; i < groupEndIndex - groupBeginIndex; i++) {
        for (int j = i + 1; j <= groupEndIndex - groupBeginIndex; j++) {
          if (group[i] > group[j]) {
            switchElements(group, i, j);
            switchElements(groupOffsets, i, j);
          }
        }
      }
      groupMedianIndex = groupOffsets[(groupEndIndex - groupBeginIndex) / 2]; // return low median index if the size
      // of data is even
    }

    return data[groupMedianIndex];
  }

  private void switchElements(int[] data, int i, int j) {
    int temp = data[i];
    data[i] = data[j];
    data[j] = temp;
  }

}
