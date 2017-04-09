public class BSTSuccessor285Inorder {
  void rec(TreeNode node, LinkedList<TreeNode> order, TreeNode target) {
    if (node.left != null) {
      rec(node.left, order, target);
    }
    if (order.size() == 2 && order.peekFirst() == target) {
      return;
    }
    if (order.size() == 2) {
      order.pollFirst();
    }
    order.add(node);
    if (node.right != null) {
      rec(node.right, order, target);
    }
  }

  public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
    if (root == null) return null;
    LinkedList<TreeNode> order = new LinkedList<>();
    rec(root, order, p);
    if (order.peekFirst() == p && order.size() == 2) {
      return order.peekLast();
    }
    return null;
  }
}

