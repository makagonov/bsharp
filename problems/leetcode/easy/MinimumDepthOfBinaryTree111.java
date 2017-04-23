public class MinimumDepthOfBinaryTree111 {
  public int minDepth(TreeNode root) {
    if (root == null) return 0;
    List<TreeNode> [] l = new LinkedList[2];
    for (int i = 0; i < 2; i++)
      l[i] = new LinkedList<>();
    l[0].add(root);
    for (int level = 1, cur = 0; l[cur].size() > 0; level++, cur = 1 - cur) {
      l[1 - cur].clear();
      for (TreeNode node : l[cur]) {
        if (node.left == null && node.right == null) return level;
        if (node.left != null) l[1 - cur].add(node.left);
        if (node.right != null) l[1 - cur].add(node.right);
      }
    }
    return -1;
  }
}
