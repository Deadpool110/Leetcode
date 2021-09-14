package leetcode;

import java.util.Arrays;

public class _offer60_dicesProbability {
    public static double[] dicesProbability1(int n) {
        double[] dp = new double[6];
        Arrays.fill(dp, 1.0 / 6.0);
        for (int i = 2; i <= n; i++) {
            double[] tmp = new double[5 * i + 1];
            for (int j = 0; j < dp.length; j++) {
                for (int k = 0; k < 6; k++) {
                    tmp[j + k] += dp[j] / 6.0;
                }
            }
            dp = tmp;
        }
        return dp;
    }

    private static double count = 0;

    public static void main(String[] args) {
        System.out.println(Arrays.toString(dicesProbability(1)));
    }
    public static double[] dicesProbability(int n) {
        int[] times = new int[n * 5 + 1];
        dfs(times, n, 0, 0);
        double[] res = new double[n * 5 + 1];
        for (int i = 0; i < n * 5 + 1; i++) {
            res[i] = (double) times[i] / count;
        }
        return res;
    }
    public static void dfs(int[] times,int n, int num, int sum) {
        if (num == n) {
            times[sum - n]++;
            count++;
            return;
        }
        for (int i = 1; i <= 6; i++) {
            dfs(times, n, num + 1, sum + i);
        }
    }
}
