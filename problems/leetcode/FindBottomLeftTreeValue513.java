public class FindBottomLeftTreeValue513 {
  public int findBottomLeftValue(TreeNode root) {
    LinkedList<TreeNode> [] levels = new LinkedList[2];
    for (int i = 0; i < 2; i++) {
      levels[i] = new LinkedList<>();
    }
    levels[0].add(root);
    int l = 0;
    for (; levels[l].size() > 0; l = 1 - l) {
      levels[1 - l].clear();
      for (TreeNode node : levels[l]) {
        if (node.left != null) levels[1 - l].add(node.left);
        if (node.right != null) levels[1 - l].add(node.right);
      }
    }
    return levels[1 - l].peekFirst().val;
  }
}
