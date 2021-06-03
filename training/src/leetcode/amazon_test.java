package leetcode;

import java.util.Arrays;

public class amazon_test {
    public static int[] search(int[] nums, int target) {
        Arrays.sort(nums);
        int[] res = new int[2];

        int l = 0, r = nums.length - 1;
        while (l < r) {
            if (target < nums[l] + nums[r]) {
                r--;
            } else if (target > nums[l] + nums[r]) {
                l++;
            } else {
                res[0] = nums[l];
                res[1] = nums[r];
                break;
            }
        }
        if (l >= r) {
            throw new IndexOutOfBoundsException();
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, -3, 6, 7};
        System.out.println(Arrays.toString(search(nums, -1)));
    }
}
