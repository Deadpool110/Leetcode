package leetcode;

public class _25_reverseKGroup {
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode h = new ListNode(0, head);
        ListNode p = h, q = head, l = p, r = head;
        int count = 0;
        while (q != null) {
            q = q.next;
            count++;
            if (count == k) {
                while (count-- > 0) {
                    l = r;
                    r = r.next;
                    l.next = p.next;
                    p.next = l;
                }
                p = head;
                head.next = q;
                head = head.next;
                l = p;
                r = head;
                count = 0;
            }
        }
        return h.next;
    }

//    模拟
    public ListNode reverseKGroup1(ListNode head, int k) {
        ListNode hair = new ListNode(0);
        hair.next = head;
        ListNode pre = hair;

        while (head != null) {
            ListNode tail = pre;
            // 查看剩余部分长度是否大于等于 k
            for (int i = 0; i < k; ++i) {
                tail = tail.next;
                if (tail == null) {
                    return hair.next;
                }
            }
            ListNode nex = tail.next;
            ListNode[] reverse = myReverse(head, tail);
            head = reverse[0];
            tail = reverse[1];
            // 把子链表重新接回原链表
            pre.next = head;
            tail.next = nex;
            pre = tail;
            head = tail.next;
        }

        return hair.next;
    }
    public ListNode[] myReverse(ListNode head, ListNode tail) {
        ListNode prev = tail.next;
        ListNode p = head;
        while (prev != tail) {
            ListNode nex = p.next;
            p.next = prev;
            prev = p;
            p = nex;
        }
        return new ListNode[]{tail, head};
    }

    public static void main(String[] args) {
        ListNode l5 = new ListNode(5);
        ListNode l4 = new ListNode(4, l5);
        ListNode l3 = new ListNode(3, l4);
        ListNode l2 = new ListNode(2, l3);
        ListNode l1 = new ListNode(1, l2);
        _25_reverseKGroup kGroup = new _25_reverseKGroup();
        ListNode node = kGroup.reverseKGroup(l1, 1);
        while (node != null) {
            System.out.println(node.val);
            node = node.next;
        }
    }
}
