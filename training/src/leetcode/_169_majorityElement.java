package leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class _169_majorityElement {
    public int _1_majorityElement(int[] nums) {
        Map<Integer, Integer> n = new HashMap<>();
        int res = 0;
        for (int num : nums) {
            n.put(num, n.getOrDefault(num, 0) + 1);
        }
        for (Map.Entry<Integer, Integer> entry : n.entrySet()) {
            if (entry.getValue() > nums.length / 2) {
                res = entry.getKey();
                break;
            }
        }
        return res;
    }

    public int _2_majorityElement(int[] nums) {
        Arrays.sort(nums);
        int res = 0, tmp = 0, i = 1;
        for (;i < nums.length; i++) {
            if (nums[i] != nums[i - 1]) {
                if (i - tmp > nums.length / 2) {
                    res = nums[tmp];
                    break;
                }
                else {
                    tmp = i;
                }
            }
        }
        if (i - tmp > nums.length / 2) {
            res = nums[tmp];
        }
        return res;
    }

//    哈希
    private Map<Integer, Integer> countNums(int[] nums) {
        Map<Integer, Integer> counts = new HashMap<Integer, Integer>();
        for (int num : nums) {
            if (!counts.containsKey(num)) {
                counts.put(num, 1);
            } else {
                counts.put(num, counts.get(num) + 1);
            }
        }
        return counts;
    }
    public int majorityElement1(int[] nums) {
        Map<Integer, Integer> counts = countNums(nums);

        Map.Entry<Integer, Integer> majorityEntry = null;
        for (Map.Entry<Integer, Integer> entry : counts.entrySet()) {
            if (majorityEntry == null || entry.getValue() > majorityEntry.getValue()) {
                majorityEntry = entry;
            }
        }

        return majorityEntry.getKey();
    }

//    排序
    public int majorityElement2(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length / 2];
    }

//    随机化
    private int randRange(Random rand, int min, int max) {
        return rand.nextInt(max - min) + min;
    }
    private int countOccurences(int[] nums, int num) {
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == num) {
                count++;
            }
        }
        return count;
    }
    public int majorityElement3(int[] nums) {
        Random rand = new Random();

        int majorityCount = nums.length / 2;

        while (true) {
            int candidate = nums[randRange(rand, 0, nums.length)];
            if (countOccurences(nums, candidate) > majorityCount) {
                return candidate;
            }
        }
    }

//    分治
    private int countInRange(int[] nums, int num, int lo, int hi) {
        int count = 0;
        for (int i = lo; i <= hi; i++) {
            if (nums[i] == num) {
                count++;
            }
        }
        return count;
    }
    private int majorityElementRec(int[] nums, int lo, int hi) {
        // base case; the only element in an array of size 1 is the majority
        // element.
        if (lo == hi) {
            return nums[lo];
        }

        // recurse on left and right halves of this slice.
        int mid = (hi - lo) / 2 + lo;
        int left = majorityElementRec(nums, lo, mid);
        int right = majorityElementRec(nums, mid + 1, hi);

        // if the two halves agree on the majority element, return it.
        if (left == right) {
            return left;
        }

        // otherwise, count each element and return the "winner".
        int leftCount = countInRange(nums, left, lo, hi);
        int rightCount = countInRange(nums, right, lo, hi);

        return leftCount > rightCount ? left : right;
    }
    public int majorityElement4(int[] nums) {
        return majorityElementRec(nums, 0, nums.length - 1);
    }

//    Boyer-Moore投票算法
    public int majorityElement5(int[] nums) {
        int count = 0;
        Integer candidate = null;

        for (int num : nums) {
            if (count == 0) {
                candidate = num;
            }
            count += (num == candidate) ? 1 : -1;
        }

        return candidate;
    }
}
