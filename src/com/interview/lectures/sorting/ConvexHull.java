package com.interview.lectures.sorting;

import com.interview.lectures.stack.LinkedStack;
import com.interview.lectures.stack.Stack;

import java.util.Arrays;

/**
 * The convex hull of a set of N points is the smallest perimeter fence enclosing the points.
 * There are 2 facts of the problem:
 * Fact1. Can traverse the convex hull by making only counterclockwise turns.
 * Fact2. The vertices of convex hull appear in increasing order of polar angle
 * with respect to point p with lowest y-coordinate.
 * <p>
 * Solution is based on the previous 2 facts:
 * 1. Choose point p with smallest y-coordinate.
 * 2. Sort points by polar angle with p.
 * 3. Consider points in order; discard unless it create a counterclockwise turn.
 * <p>
 * About counter clockwise:
 * by calculate (b.x-a.x) *(c.y-a.y)- (b.y-a.y)*(c.x-a.x),
 * if the result > 0, they are clockwise,
 * if result < 0, they ain't clockwise,
 * if result = 0, they are in a line
 *
 * @author stefaniezhao
 */
public class ConvexHull {

  public static Point[] grahamScan(Point[] points) {
    Stack<Point> hull = new LinkedStack<Point>();

    //1. sort point based on Y-coordinate to find p0.
    //getLowestY(points);
    Arrays.sort(points, Point.BY_Y_AXIS);
    //2. sort pints by polar angle with respect to p0.
    Point.sortByPolarAngle(points);

//		for(Point p : points){
//			System.out.print(p.toString() + ", ");
//		}
//		System.out.println();

    //find the edges
    hull.push(points[0]);
    hull.push(points[1]);

    for (int i = 2; i < points.length; i++) {
      Point top = hull.pop();
      //System.out.println(top.toString());
      while (Point.counterclockwise(hull.peek(), top, points[i]) <= 0) {
        //System.out.println(top.toString());
        top = hull.pop();
      }
      hull.push(top);
      hull.push(points[i]);
    }

    int size = hull.size();
    Point[] edges = new Point[size];
    int index = 0;
    while (!hull.isEmpty()) {
      edges[index] = hull.pop();
      index++;
    }
    return edges;
  }

  public static void getLowestY(Point[] points) {
    int min = 0;
    for (int i = 1; i < points.length; i++) {
      if (Point.isLower(points[i], points[min]) > 0) {
        min = i;
      }
    }
    //swap min and 0
    Point temp = points[0];
    points[0] = points[min];
    points[min] = temp;
  }

  private static Point[] generateTestPoint() {
    Point[] testPoint = new Point[10];
    //String pointStr = "0,0#1,0.5#1,1#2,1.5#0.5,1.5#1,2#0,2#0,1#-0.5,1";
    //String pointStr = "7,1#0,4#8,8#3,6#5,3#6,5#4,0#9,9#2,7#1,2";
    String pointStr = "8,4#9,2#4,9#1,5#0,6#7,7#5,8#3,1#6,3#2,0";
    String[] points = pointStr.split("#");
    for (int i = 0; i < points.length; i++) {
      String[] coords = points[i].split(",");
      double x = Double.parseDouble(coords[0]);
      double y = Double.parseDouble(coords[1]);
      testPoint[i] = new Point(x, y);
    }
    return testPoint;
  }

  public static void main(String[] args) {
    Point[] testPoint = ConvexHull.generateTestPoint();
    for (Point p : testPoint) {
      System.out.print(p + ", ");
    }
    System.out.println();
    Point[] edges = ConvexHull.grahamScan(testPoint);
    for (Point p : edges) {
      System.out.print(p + ", ");
    }
  }

}
