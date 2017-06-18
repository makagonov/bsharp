/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class BinaryTreeRightSideView199 {
  public List<Integer> rightSideView(TreeNode root) {
    List<Integer> res = new LinkedList<>();
    if (root == null) return res;

    LinkedList<TreeNode> [] levels = new LinkedList[2];
    for (int i = 0; i < 2; i++) {
      levels[i] = new LinkedList<>();
    }
    levels[0].add(root);

    for (int cur = 0; levels[cur].size() > 0; cur = 1 - cur) {
      levels[1 - cur].clear();
      res.add(levels[cur].peekLast().val);
      for (TreeNode levelNode : levels[cur]) {
        if (levelNode.left != null) levels[1 - cur].add(levelNode.left);
        if (levelNode.right != null) levels[1 - cur].add(levelNode.right);
      }
    }

    return res;
  }
}
