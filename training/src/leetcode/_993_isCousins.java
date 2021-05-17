package leetcode;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public class _993_isCousins {
    public boolean isCousins(TreeNode root, int x, int y) {
        if (root.val == x || root.val == y) {
            return false;
        }
        Deque<TreeNode> queue = new LinkedList<>();
        queue.addLast(root);
        while (!queue.isEmpty()) {
            boolean X = false, Y = false;
            int len = queue.size();
            for (int i = 0; i < len; i++) {
                TreeNode node = queue.pollFirst();
                if (node.left != null) {
                    if (node.left.val == x) {
                        X = true;
                    }
                    if (node.left.val == y) {
                        Y = true;
                    }
                    queue.addLast(node.left);
                }
                if (node.right != null) {
                    if (node.right.val == x) {
                        if (node.left != null && node.left.val == y) {
                            return false;
                        }
                        X = true;
                    }
                    if (node.right.val == y) {
                        if (node.left != null && node.left.val == x) {
                            return false;
                        }
                        Y = true;
                    }
                    queue.addLast(node.right);
                }
            }
            if (X && Y) {
                return true;
            } else if (!X && !Y) {
                continue;
            }
            return false;
        }
        return false;
    }

//    深度优先搜索
    // x 的信息
    int x;
    TreeNode xParent;
    int xDepth;
    boolean xFound = false;
    // y 的信息
    int y;
    TreeNode yParent;
    int yDepth;
    boolean yFound = false;

    public boolean isCousins1(TreeNode root, int x, int y) {
        this.x = x;
        this.y = y;
        dfs(root, 0, null);
        return xDepth == yDepth && xParent != yParent;
    }
    public void dfs(TreeNode node, int depth, TreeNode parent) {
        if (node == null) {
            return;
        }

        if (node.val == x) {
            xParent = parent;
            xDepth = depth;
            xFound = true;
        } else if (node.val == y) {
            yParent = parent;
            yDepth = depth;
            yFound = true;
        }

        // 如果两个节点都找到了，就可以提前退出遍历
        // 即使不提前退出，对最坏情况下的时间复杂度也不会有影响
        if (xFound && yFound) {
            return;
        }

        dfs(node.left, depth + 1, node);

        if (xFound && yFound) {
            return;
        }

        dfs(node.right, depth + 1, node);
    }

//    广度优先搜索
    public boolean isCousins2(TreeNode root, int x, int y) {
        this.x = x;
        this.y = y;

        Queue<TreeNode> nodeQueue = new LinkedList<TreeNode>();
        Queue<Integer> depthQueue = new LinkedList<Integer>();
        nodeQueue.offer(root);
        depthQueue.offer(0);
        update(root, null, 0);

        while (!nodeQueue.isEmpty()) {
            TreeNode node = nodeQueue.poll();
            int depth = depthQueue.poll();
            if (node.left != null) {
                nodeQueue.offer(node.left);
                depthQueue.offer(depth + 1);
                update(node.left, node, depth + 1);
            }
            if (node.right != null) {
                nodeQueue.offer(node.right);
                depthQueue.offer(depth + 1);
                update(node.right, node, depth + 1);
            }
            if (xFound && yFound) {
                break;
            }
        }

        return xDepth == yDepth && xParent != yParent;
    }
    // 用来判断是否遍历到 x 或 y 的辅助函数
    public void update(TreeNode node, TreeNode parent, int depth) {
        if (node.val == x) {
            xParent = parent;
            xDepth = depth;
            xFound = true;
        } else if (node.val == y) {
            yParent = parent;
            yDepth = depth;
            yFound = true;
        }
    }
}
