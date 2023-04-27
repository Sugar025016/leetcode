package org.example;

import java.util.ArrayList;
import java.util.List;

/**
 * 2407. Longest Increasing Subsequence II
 * https://leetcode.com/problems/longest-increasing-subsequence-ii/
 */
public class LongestIncreasingSubsequence2 {
    public int lengthOfLIS(int[] nums, int k) {

        List<List<Integer>> mapList = new ArrayList<>();
        List<Integer> lists = new ArrayList<>();
        lists.add(nums[nums.length - 1]);
        mapList.add(lists);

        for (int z = nums.length - 2; z >= 0; z--) {
            int x;
            int y = 0;
            int count = 1;
            boolean mach = false;
            boolean bb = false;
            int num = nums[z];
            int i1 = 999999999;

            for (int i = mapList.size() - 1; i >= 0; i--) {
                count = i;
                x = 0;
                int n = 0;
                int m = mapList.get(i).size() - 1;
                boolean aa = false;
                int node = m;
                while (m >= n) {
                    if (num >= mapList.get(i).get(node)) {
                        if (num == mapList.get(i).get(node)) {
                            aa = true;
                        }
                        n = node + 1;
                        x = node + 1;
                        if (m == mapList.get(i).size() - 1) {
                            break;
                        }
                    } else if (num < mapList.get(i).get(node)) {
                        if (mapList.get(i).get(node) - num < i1) {
                            i1 = mapList.get(i).get(node) - num;
                            x = node;
                        }
                        if (i1 <= k && i1 > 0) {
                            mach = true;
                        }
                        m = node - 1;
                    }
                    node = (n + m) / 2;
                }
                if (mach) {
                    count++;
                    break;
                } else {
                    y = x;
                    bb = aa;
                }
            }
            if (mach && count >= mapList.size()) {
                List<Integer> list = new ArrayList<>();
                list.add(num);
                mapList.add(list);
            } else if (!bb) {
                mapList.get(count).add(y, num);
            }

        }

        return mapList.size();
    }
}
