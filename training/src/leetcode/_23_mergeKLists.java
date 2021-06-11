package leetcode;

import java.util.PriorityQueue;

public class _23_mergeKLists {
    public ListNode mergeKLists(ListNode[] lists) {
        ListNode head = new ListNode(0);
        if (lists.length >= 1) {
            head.next = lists[0];
        }
        ListNode p = head;
        for (int i = 1; i < lists.length; i++) {
            ListNode node = lists[i];
            while (node != null) {
                if (p.next == null) {
                    p.next = node;
                    break;
                } else {
                    if (p.next.val > node.val) {
                        ListNode n = node;
                        node = node.next;
                        n.next = p.next;
                        p.next = n;
                    }
                    p = p.next;
                }
            }
            p = head;
        }
        return head.next;
    }

//    顺序合并
    public ListNode mergeKLists1(ListNode[] lists) {
        ListNode ans = null;
        for (int i = 0; i < lists.length; ++i) {
            ans = mergeTwoLists(ans, lists[i]);
        }
        return ans;
    }
    public ListNode mergeTwoLists(ListNode a, ListNode b) {
        if (a == null || b == null) {
            return a != null ? a : b;
        }
        ListNode head = new ListNode(0);
        ListNode tail = head, aPtr = a, bPtr = b;
        while (aPtr != null && bPtr != null) {
            if (aPtr.val < bPtr.val) {
                tail.next = aPtr;
                aPtr = aPtr.next;
            } else {
                tail.next = bPtr;
                bPtr = bPtr.next;
            }
            tail = tail.next;
        }
        tail.next = (aPtr != null ? aPtr : bPtr);
        return head.next;
    }

//    分治合并
    public ListNode mergeKLists2(ListNode[] lists) {
        return merge(lists, 0, lists.length - 1);
    }
    public ListNode merge(ListNode[] lists, int l, int r) {
        if (l == r) {
            return lists[l];
        }
        if (l > r) {
            return null;
        }
        int mid = (l + r) >> 1;
        return mergeTwoLists(merge(lists, l, mid), merge(lists, mid + 1, r));
    }

//    优先队列
    class Status implements Comparable<Status> {
        int val;
        ListNode ptr;

        Status(int val, ListNode ptr) {
            this.val = val;
            this.ptr = ptr;
        }

        public int compareTo(Status status2) {
            return this.val - status2.val;
        }
    }
    PriorityQueue<Status> queue = new PriorityQueue<Status>();
    public ListNode mergeKLists3(ListNode[] lists) {
        for (ListNode node: lists) {
            if (node != null) {
                queue.offer(new Status(node.val, node));
            }
        }
        ListNode head = new ListNode(0);
        ListNode tail = head;
        while (!queue.isEmpty()) {
            Status f = queue.poll();
            tail.next = f.ptr;
            tail = tail.next;
            if (f.ptr.next != null) {
                queue.offer(new Status(f.ptr.next.val, f.ptr.next));
            }
        }
        return head.next;
    }
}
