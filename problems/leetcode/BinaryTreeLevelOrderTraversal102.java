public class BinaryTreeLevelOrderTraversal102 {
  public List<List<Integer>> levelOrder(TreeNode root) {
    List<List<Integer>> result = new ArrayList<>();
    if (root == null) return result;
    List<TreeNode> [] levels = new ArrayList[2];
    for (int i = 0; i < 2; i++) {
      levels[i] = new ArrayList<>();
    }
    levels[0].add(root);
    for (int cur = 0; levels[cur].size() > 0; cur ^= 1) {
      levels[cur^1].clear();
      List<Integer> levelRes = new ArrayList<>();
      for (TreeNode node : levels[cur]) {
        levelRes.add(node.val);
        if (node.left != null)  levels[1 - cur].add(node.left);
        if (node.right != null) levels[1 - cur].add(node.right);
      }
      result.add(levelRes);
    }
    return result;
  }
}
