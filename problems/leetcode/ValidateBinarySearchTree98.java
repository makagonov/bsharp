/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
  final long oo = Long.MAX_VALUE;
  public boolean isValidBST(TreeNode root) {
    return isValid(root, -oo, oo);
  }

  private boolean isValid(TreeNode node, long min, long max) {
    if (node == null) return true;
    if (node.val < min || node.val > max)
      return false;
    boolean valid = true;
    valid &= isValid(node.left, Math.max(min, -oo), Math.min(max, getVal(node.val, - 1)));
    return valid && isValid(node.right, Math.max(min, getVal(node.val, 1)), Math.min(max, oo));
  }

  private long getVal(int val, int inc) {
    long longVal = (long)val;
    return longVal + inc;
  }
}
