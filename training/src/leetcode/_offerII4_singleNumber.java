package leetcode;

import java.util.ArrayList;
import java.util.List;

public class _offerII4_singleNumber {
    public static int singleNumber(int[] nums) {
        List<Integer> list = new ArrayList<>();
        long sum1 = 0, sum2 = 0;
        for (int num : nums) {
            if (!list.contains(num)) {
                sum1 += num;
                list.add(num);
            }
            sum2 += num;
        }
        sum1 *= 3;
        return (int) ((sum1 - sum2) / 2);
    }

    public static void main(String[] args) {
        int[] nums = {43,16,45,89,45,-2147483648,45,2147483646,-2147483647,-2147483648,43,2147483647,-2147483646,-2147483648,89,-2147483646,89,-2147483646,-2147483647,2147483646,-2147483647,16,16,2147483646,43};

        System.out.println(singleNumber(nums));
    }
}
