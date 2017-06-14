/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class SumOfLeftLeaves404 {
  public int sumOfLeftLeaves(TreeNode root) {
    if (root == null) return 0;
    return getSum(root.left, true) + getSum(root.right, false);
  }

  int getSum(TreeNode node, boolean canUse) {
    if (node == null) return 0;
    if (node.left == null && node.right == null && canUse) return node.val;
    return getSum(node.left, true) + getSum(node.right, false);
  }
}
