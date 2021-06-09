package leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ByteDance_faceTest {
    public static void main(String[] args) {
        int[][] ope = {{1, 1, 1}, {1, 2, 2}, {1, 3, 2}, {2, 1}, {1, 4, 4}, {2, 2}};
        ByteDance_faceTest lru = new ByteDance_faceTest();
        System.out.println(Arrays.toString(lru.LRU(ope, 3)));
    }

    Map<Integer, LinkNode> map = new HashMap<Integer, LinkNode>();
    int count = 0;
    int max;
    LinkNode head = new LinkNode(0, 0);
    LinkNode tail = new LinkNode(-1, -1);
    public int[] LRU (int[][] operators, int k) {
        head.next = tail;
        tail.pre = head;
        max = k;

        int n = 0;
        for (int i = 0; i < operators.length; i++) {
            if (operators[i][0] == 2) {
                n++;
            }
        }
        int[] res = new int[n];
        n = 0;
        for (int i = 0; i < operators.length; i++) {
            if (operators[i][0] == 1) {
                set(operators[i][1], operators[i][2]);
            } else {
                int val = get(operators[i][1]);
                res[n] = val;
                n++;
            }
        }
        return res;
    }

    public void set(int key, int val) {
        if (map.containsKey(key)) {
            LinkNode node = map.get(key);
            node.val = val;

            node.pre.next = node.next;
            node.next.pre = node.pre;

            head.next.pre = node;
            node.next = head.next;
            head.next = node;
            node.pre = head;
        } else {
            LinkNode node = new LinkNode(key, val);
            map.put(key, node);
            if (count == max) {
                LinkNode del = tail.pre;
                map.remove(del.key);

                tail.pre.pre.next = tail;
                tail.pre = tail.pre.pre;
            } else {
                count++;
            }
            head.next.pre = node;
            node.next = head.next;
            head.next = node;
            node.pre = head;
        }
    }

    public int get(int key) {
        if (map.containsKey(key)) {
            LinkNode node = map.get(key);

            node.pre.next = node.next;
            node.next.pre = node.pre;

            head.next.pre = node;
            node.next = head.next;
            head.next = node;
            node.pre = head;
            return node.val;
        } else {
            return -1;
        }
    }
}

class LinkNode {
    int key;
    int val;
    LinkNode pre;
    LinkNode next;

    public LinkNode(int key, int val) {
        this.key = key;
        this.val = val;
    }
}
