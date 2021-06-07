package leetcode;

import java.util.Arrays;

public class huawei_test {
    public static void main(String[] args) {
        System.out.println(max(987123));
    }
    public static int max(int num) {
        if (num < 10) {
            return num;
        }
        int[] number = new int[9];
        int n = 0;
        while (num != 0) {
            number[n] = num % 10;
            num /= 10;
            n++;
        }
        int[] max = Arrays.copyOf(number, n);
        Arrays.sort(max);
        int i = n - 1;
        for (; i >= 0; i--) {
            if (max[i] != number[i]) {
                break;
            }
        }
        if (i > 0) {
            int tmp = max[i], a = i, b = i;
            for (; i >= 0; i--) {
                if (number[i] == tmp) {
                    b = i;
                }
            }
            tmp = number[a];
            number[a] = number[b];
            number[b] = tmp;
        }
        int res = 0;
        for (i = n - 1; i >= 0; i--) {
            res = res * 10 + number[i];
        }
        return res;
    }
}
