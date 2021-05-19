package leetcode;

import java.util.*;

public class _1738_kthLargestValue {
    public int kthLargestValue(int[][] matrix, int k) {
        int m = matrix.length, n = matrix[0].length;
        int[] XOR = new int[m * n];
        XOR[0] = matrix[0][0];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int tmp = matrix[i][j];
                if (i != 0) {
                    tmp ^= XOR[(i - 1) * n + j];
                }
                if (j != 0) {
                    tmp ^= XOR[i * n + j - 1];
                }
                if (i != 0 && j != 0) {
                    tmp ^= XOR[(i - 1) * n + j - 1];
                }
                XOR[i * n + j] = tmp;
            }
        }
        Arrays.sort(XOR);
        return XOR[m * n - k];
    }

//    二维前缀和+排序
    public int kthLargestValue1(int[][] matrix, int k) {
        int m = matrix.length, n = matrix[0].length;
        int[][] pre = new int[m + 1][n + 1];
        List<Integer> results = new ArrayList<Integer>();
        for (int i = 1; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                pre[i][j] = pre[i - 1][j] ^ pre[i][j - 1] ^ pre[i - 1][j - 1] ^ matrix[i - 1][j - 1];
                results.add(pre[i][j]);
            }
        }

        Collections.sort(results, new Comparator<Integer>() {
            public int compare(Integer num1, Integer num2) {
                return num2 - num1;
            }
        });
        return results.get(k - 1);
    }

//    二维前缀和+快速选择算法
    public int kthLargestValue2(int[][] matrix, int k) {
        int m = matrix.length, n = matrix[0].length;
        int[][] pre = new int[m + 1][n + 1];
        List<Integer> results = new ArrayList<Integer>();
        for (int i = 1; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                pre[i][j] = pre[i - 1][j] ^ pre[i][j - 1] ^ pre[i - 1][j - 1] ^ matrix[i - 1][j - 1];
                results.add(pre[i][j]);
            }
        }

        nthElement(results, 0, k - 1, results.size() - 1);
        return results.get(k - 1);
    }
    public void nthElement(List<Integer> results, int left, int kth, int right) {
        if (left == right) {
            return;
        }
        int pivot = (int) (left + Math.random() * (right - left + 1));
        swap(results, pivot, right);
        // 三路划分（three-way partition）
        int sepl = left - 1, sepr = left - 1;
        for (int i = left; i <= right; i++) {
            if (results.get(i) > results.get(right)) {
                swap(results, ++sepr, i);
                swap(results, ++sepl, sepr);
            } else if (results.get(i) == results.get(right)) {
                swap(results, ++sepr, i);
            }
        }
        if (sepl < left + kth && left + kth <= sepr) {
            return;
        } else if (left + kth <= sepl) {
            nthElement(results, left, kth, sepl);
        } else {
            nthElement(results, sepr + 1, kth - (sepr - left + 1), right);
        }
    }
    public void swap(List<Integer> results, int index1, int index2) {
        int temp = results.get(index1);
        results.set(index1, results.get(index2));
        results.set(index2, temp);
    }
}
