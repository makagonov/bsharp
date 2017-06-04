/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class SymmetricTree101 {
  public boolean isSymmetric(TreeNode root) {
    if (root == null) return true;
    return areSymmetric(root.left, root.right);
  }

  boolean areSymmetric(TreeNode a, TreeNode b) {
    if (a == null || b == null) return a == b;
    if (a.val != b.val) return false;
    if (!areSymmetric(a.left, b.right)) return false;
    return areSymmetric(a.right, b.left);
  }
}
