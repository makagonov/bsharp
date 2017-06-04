/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class BinaryTreeUpsideDown156 {
  public TreeNode upsideDownBinaryTree(TreeNode root) {
    if (root == null || root.left == null) return root;
    TreeNode cur = root, prev = null, next = null, leaf = null;
    while(cur != null) {
      next = cur.left;
      cur.left = leaf;

      leaf = cur.right;
      cur.right = prev;

      prev = cur;
      cur = next;
    }
    return prev;
  }
}
