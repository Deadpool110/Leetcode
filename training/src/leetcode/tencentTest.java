package leetcode;

public class tencentTest {
    public static void main(String[] args) {

    }

    public static LinkedNode find(LinkedNode head) {
        if (head == null || head.next == null) {
            return null;
        }
        LinkedNode p = head;
        LinkedNode q = head.next;
        while (q != null && p != q) {
            p = p.next;
            q = q.next;
            if (q == null) {
                break;
            }
            q = q.next;
        }
        if (q == null) {
            return null;
        }
        int len = 1;
        p = p.next;
        while (p != q) {
            len++;
            p = p.next;
        }
        p = head;
        q = head;
        for (int i = 0; i < len; i++) {
            p = p.next;
        }
        while (p != q) {
            p = p.next;
            q = q.next;
        }
        return p;
    }

    private static class LinkedNode {
        int value;
        LinkedNode next;

        public LinkedNode(int value) {
            this.value = value;
        }
    }
}
