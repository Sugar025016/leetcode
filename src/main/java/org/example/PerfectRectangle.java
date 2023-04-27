package org.example;

import java.util.ArrayList;
import java.util.List;


/**
 * 391. Perfect Rectangle
 * https://leetcode.com/problems/perfect-rectangle/
 */
public class PerfectRectangle {
    public boolean isRectangleCover(int[][] rectangles) {


        List<List<Integer>> lists = new ArrayList<>();
        List<Integer> maxList = new ArrayList<>();
        if (rectangles.length == 0 || rectangles[0].length == 0) {
            return false;
        } else {
            List<Integer> list = new ArrayList<>();
            list.add(rectangles[0][0]);
            list.add(rectangles[0][1]);
            list.add(rectangles[0][2]);
            list.add(rectangles[0][3]);
            maxList.add(rectangles[0][0]);
            maxList.add(rectangles[0][1]);
            maxList.add(rectangles[0][2]);
            maxList.add(rectangles[0][3]);

            lists.add(list);
        }
        int sum = (rectangles[0][2] - rectangles[0][0]) * (rectangles[0][3] - rectangles[0][1]);
        for (int i = 1; i < rectangles.length; i++) {

            int num0 = rectangles[i][0];
            int num1 = rectangles[i][1];
            int num2 = rectangles[i][2];
            int num3 = rectangles[i][3];

            int node = 0;
            int n = 0;
            int m = lists.size() - 1;

            for (int j = 0; j < rectangles[i].length; j++) {
                int[] startEndNode = node(rectangles[i][j], lists, n, m, j);
                if (startEndNode[0] > startEndNode[1]) {
                    node = startEndNode[0];
                    break;
                } else if (j == 3 && startEndNode[0] == startEndNode[1]) {
                    return false;
                }
                n = startEndNode[0];
                m = startEndNode[1];

            }


            for (int j = 0; j < rectangles[i].length; j++) {
                int a = 0;
                if (j == 0) {
                    a = 2;
                } else if (j == 1) {
                    a = 3;
                } else if (j == 3) {
                    a = 1;
                }
                int startEnd = startEnd(rectangles[i][a], lists, n, m, j);
                if (j == 0 || j == 1) {
                    m = startEnd;
                } else {
                    n = startEnd;
                }
            }


            for (int j = n; j <= m && j < lists.size(); j++) {
                if (num0 < lists.get(j).get(2) && num1 < lists.get(j).get(3) && num2 > lists.get(j).get(0) && num3 > lists.get(j).get(1)) {
                    return false;
                }
            }


            List<Integer> integers = new ArrayList<>();
            integers.add(rectangles[i][0]);
            integers.add(rectangles[i][1]);
            integers.add(rectangles[i][2]);
            integers.add(rectangles[i][3]);
            lists.add(node, integers);

            maxList.set(0, Math.min(rectangles[i][0], maxList.get(0)));
            maxList.set(1, Math.min(rectangles[i][1], maxList.get(1)));
            maxList.set(2, Math.max(rectangles[i][2], maxList.get(2)));
            maxList.set(3, Math.max(rectangles[i][3], maxList.get(3)));
            sum = sum + (num2 - num0) * (num3 - num1);
        }

        int i = (maxList.get(2) - maxList.get(0)) * (maxList.get(3) - maxList.get(1));


        return sum == i;
    }

    int[] node(int num, List<List<Integer>> lists, int n, int m, int i) {
        int[] rNode = new int[2];
        int n1 = n;
        int m1 = m;
        int n2 = n;
        int m2 = m;
        while (m1 >= n1 || m2 >= n2) {
            if (m1 >= n1) {
                int node1 = (n1 + m1) / 2;
                if (num > lists.get(node1).get(i)) {
                    n1 = node1 + 1;
                    rNode[0] = node1 + 1;
                } else {
                    m1 = node1 - 1;
                    rNode[0] = node1;
                }
            }

            if (m2 >= n2) {
                int node2 = (n2 + m2) / 2;
                if (num >= lists.get(node2).get(i)) {
                    n2 = node2 + 1;
                    rNode[1] = node2;
                } else {
                    m2 = node2 - 1;
                    rNode[1] = node2 - 1;
                }
            }

        }

        return rNode;
    }

    int startEnd(int num, List<List<Integer>> lists, int n, int m, int i) {
        int node = 0;
        while (m >= n) {
            int node1 = (n + m) / 2;
            if (num > lists.get(node1).get(i)) {
                n = node1 + 1;
                node = node1;
            } else {
                m = node1 - 1;
                node = node1;
            }
        }

        return node;
    }

}
