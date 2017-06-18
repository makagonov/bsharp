/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class BinaryTreeLongestConsecutiveSequenceII549 {
  class Result {
    int increasing = 1;
    int decreasing = 1;
    int best = 1;
  }
  public int longestConsecutive(TreeNode root) {
    if (root == null) return 0;
    Result res = getResultForNode(root);
    return res.best;
  }

  void updateResult(TreeNode node, TreeNode child, Result nodeRes, Result childRes) {
    if (node.val + 1 == child.val) {
      nodeRes.increasing = max(nodeRes.increasing, 1 + childRes.increasing);
    } else  if (node.val - 1 == child.val) {
      nodeRes.decreasing = max(nodeRes.decreasing, 1 + childRes.decreasing);
    }
    nodeRes.best = max(nodeRes.best, childRes.best, nodeRes.increasing, nodeRes.decreasing);
  }

  private int max(int...vals) {
    int max = 0;
    for(int i : vals)
      if (i > max)
        max = i;
    return max;
  }

  private Result getResultForNode(TreeNode node) {
    Result result = new Result();
    if (node.left == null && node.right == null) return result;

    Result leftResult = null;
    Result rightResult = null;
    if (node.left != null) {
      leftResult = getResultForNode(node.left);
      updateResult(node, node.left, result, leftResult);
    }
    if (node.right != null) {
      rightResult = getResultForNode(node.right);
      updateResult(node, node.right, result, rightResult);
    }

    if (node.left == null || node.right == null) return result;
    if (node.val - 1 == node.left.val && node.val + 1 == node.right.val) {
      result.best = max(result.best, 1 + leftResult.decreasing + rightResult.increasing);
    } else if (node.val + 1 == node.left.val && node.val - 1 == node.right.val) {
      result.best = max(result.best, 1 + leftResult.increasing + rightResult.decreasing);
    }
    return result;
  }
}
