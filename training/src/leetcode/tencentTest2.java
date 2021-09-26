package leetcode;

public class tencentTest2 {
    private static class ListNode {
        int val;
        ListNode next;

        public ListNode(int val) {
            this.val = val;
        }
    }


    public static ListNode delete(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode p = head;
        ListNode q = head.next;
        while (q != null) {
            if (p.val != q.val) {
                p = p.next;
                q = q.next;
            } else {
                q = q.next;
                p.next = q;
            }
        }
        return head;
    }

    public static void main(String[] args) {
        double n = 2.345;
        System.out.println();
    }
}
