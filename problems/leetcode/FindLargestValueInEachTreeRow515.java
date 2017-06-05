/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class FindLargestValueInEachTreeRow515 {
  public List<Integer> largestValues(TreeNode root) {
    if (root == null) return new ArrayList<>();
    List<Integer> res = new ArrayList<>();

    Set<TreeNode> [] sets = new HashSet[2];
    for (int i = 0; i < 2; i++) {
      sets[i] = new HashSet<>();
    }
    sets[0].add(root);
    for (int cur = 0; sets[cur].size() > 0; cur = 1 - cur) {
      sets[1 - cur].clear();
      int max = Integer.MIN_VALUE;
      for (TreeNode node : sets[cur]) {
        max = Math.max(max, node.val);
        if (node.left != null) sets[1 - cur].add(node.left);
        if (node.right != null) sets[1 - cur].add(node.right);
      }
      res.add(max);
    }
    return res;
  }
}
