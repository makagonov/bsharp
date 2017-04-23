public class BalancedBinaryTree104 {
  int getHeight(TreeNode root) {
    if (root == null) return 0;
    int leftHeight = getHeight(root.left);
    if (leftHeight < 0) return -1;
    int rightHeight = getHeight(root.right);
    if (rightHeight < 0) return -1;
    if (Math.abs(leftHeight - rightHeight) > 1) return -1;
    return 1 + Math.max(leftHeight, rightHeight);
  }
  public boolean isBalanced(TreeNode root) {
    if (root == null) return true;
    int treeHeight = getHeight(root);
    return treeHeight >= 0;
  }
}
