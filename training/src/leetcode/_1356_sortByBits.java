package leetcode;

import java.util.*;

public class _1356_sortByBits {
    public int[] sortByBits(int[] arr) {
        List<List<Integer>> res = new ArrayList<>();
        Map<Integer, Integer> num = new HashMap<>();
        Arrays.sort(arr);
        for (int n : arr) {
            int i = n;
            int tmp = 0;
            while (i != 0) {
                i &= i - 1;
                tmp++;
            }
            if (!num.containsKey(tmp)) {
                List<Integer> re = new ArrayList<>();
                re.add(n);
                res.add(re);
                num.put(tmp, res.size() - 1);
            }
            else {
                Integer index = num.get(tmp);
                res.get(index).add(n);
            }
        }
        int[] result = new int[arr.length];
        int i = 0, j = 0;
        while (!num.isEmpty()) {
            if (num.containsKey(j)) {
                Integer index = num.get(j);
                List<Integer> list = res.get(index);
                for (Integer integer : list) {
                    result[i] = integer;
                    i++;
                }
                num.remove(j);
            }
            j++;
        }
        return result;
    }

    public int[] sortByBits1(int[] arr) {
        int[] bit = new int[10001];
        List<Integer> list = new ArrayList<Integer>();
        for (int x : arr) {
            list.add(x);
            bit[x] = get(x);
        }
        list.sort(new Comparator<Integer>() {
            public int compare(Integer x, Integer y) {
                if (bit[x] != bit[y]) {
                    return bit[x] - bit[y];
                } else {
                    return x - y;
                }
            }
        });
        for (int i = 0; i < arr.length; ++i) {
            arr[i] = list.get(i);
        }
        return arr;
    }
    public int get(int x) {
        int res = 0;
        while (x != 0) {
            res += x % 2;
            x /= 2;
        }
        return res;
    }

    public int[] sortByBits2(int[] arr) {
        List<Integer> list = new ArrayList<Integer>();
        for (int x : arr) {
            list.add(x);
        }
        int[] bit = new int[10001];
        for (int i = 1; i <= 10000; ++i) {
            bit[i] = bit[i >> 1] + (i & 1);
        }
        list.sort((x, y) -> {
            if (bit[x] != bit[y]) {
                return bit[x] - bit[y];
            } else {
                return x - y;
            }
        });
        for (int i = 0; i < arr.length; ++i) {
            arr[i] = list.get(i);
        }
        return arr;
    }
}
