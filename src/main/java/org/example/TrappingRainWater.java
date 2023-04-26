package org.example;

import java.util.ArrayList;
import java.util.List;

/**
 * 42. Trapping Rain Water
 * https://leetcode.com/problems/trapping-rain-water/
 */
public class TrappingRainWater {
    public int trap(int[] height) {
        if (height.length < 3) {
            return 0;
        }
        int b = 0;
        int by;
        int h = 0;
        int hy = 0;
        List<Integer> heightList = new ArrayList<>();
        for (int i = 0; i < height.length; i++) {
            if (height[i] > h) {
                h = height[i];
                hy = i;
            } else if (height[i] == h && hy == i - 1) {
                hy = i;
            }
            heightList.add(height[i]);
        }
        heightList.add(0);
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i < heightList.size(); i++) {
            if (heightList.get(i - 1) > heightList.get(i)) {
                if (b < heightList.get(i - 1)) {
                    b = heightList.get(i - 1);
                    by = i - 1;
                    if (list.size() < 1) {
                        list.add(i - 1);
                    } else {
                        if (i - 1 <= hy) {
                            if (heightList.get(list.get(list.size() - 1)) > b) {
                            } else {
                                list.add(by);
                            }
                        } else {
                            for (int j = list.size() - 1; j >= 1; j--) {
                                if (heightList.get(list.get(j)) < b) {
                                    //                                    if (heightList.get(list.get(j)) < b && list.get(j)<h) {
                                    list.remove(j);
                                } else {
                                    break;
                                }
                            }
                            list.add(by);
                        }
                    }
                }
            } else {
                b = 0;
            }
        }

        int min;
        int stone = 0;
        int sum = 0;
        int aa;
        int bb;

        for (int i = 1; i < list.size(); i++) {
            aa = list.get(i - 1);
            bb = list.get(i);
            min = Math.min(height[aa], height[bb]);
            for (int j = aa + 1; j < bb; j++) {
                stone = stone + (height[j] > min ? min : height[j]);
            }

            sum = sum + min * (bb - aa - 1) - stone;
            stone = 0;
        }
        return sum;
    }
}
