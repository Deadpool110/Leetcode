package leetcode;

public class _222_countNodes {
    public int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return sum(root, 1);
    }
    public int sum(TreeNode root, int res) {
        if (root.left != null) {
            res++;
            res = sum(root.left, res);
        }
        if (root.right != null) {
            res++;
            res = sum(root.right, res);
        }
        return res;
    }

    public int countNodes1(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int level = 0;
        TreeNode node = root;
        while (node.left != null) {
            level++;
            node = node.left;
        }
        int low = 1 << level, high = (1 << (level + 1)) - 1;
        while (low < high) {
            int mid = (high - low + 1) / 2 + low;
            if (exists(root, level, mid)) {
                low = mid;
            } else {
                high = mid - 1;
            }
        }
        return low;
    }
    public boolean exists(TreeNode root, int level, int k) {
        int bits = 1 << (level - 1);
        TreeNode node = root;
        while (node != null && bits > 0) {
            if ((bits & k) == 0) {
                node = node.left;
            } else {
                node = node.right;
            }
            bits >>= 1;
        }
        return node != null;
    }
}
