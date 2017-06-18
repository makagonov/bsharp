/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class MinimumAbsoluteDifferenceInBST530 {
  class MinResult {
    int min = Integer.MAX_VALUE;
    void offer(int val) {
      if (val < min) min = val;
    }
  }

  void dfs(TreeNode node, LinkedList<Integer> lastTwo, MinResult result) {
    if (node.left != null) dfs(node.left, lastTwo, result);
    if (lastTwo.size() == 2) lastTwo.pollFirst();
    lastTwo.add(node.val);
    if (lastTwo.size() == 2) result.offer(Math.abs(lastTwo.peekFirst() - lastTwo.peekLast()));
    if (node.right != null) dfs(node.right, lastTwo, result);
  }

  public int getMinimumDifference(TreeNode root) {
    MinResult res = new MinResult();
    dfs(root, new LinkedList<>(), res);
    return res.min;
  }
}
