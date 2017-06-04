public class DiameterOfBinaryTree543 {
  class MaxHolder {
    int max = 0;
    void offer(int val) {
      if (val > max) max = val;
    }
  }
  public int diameterOfBinaryTree(TreeNode root) {
    MaxHolder holder = new MaxHolder();
    dfs(root, holder);
    return holder.max;
  }

  int dfs(TreeNode node, MaxHolder holder) {
    if (node == null) return 0;
    int left = dfs(node.left, holder);
    int right = dfs(node.right, holder);
    holder.offer(left + right);
    return 1 + Math.max(left, right);
  }
}
