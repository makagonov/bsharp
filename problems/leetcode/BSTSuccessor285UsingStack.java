public class BSTSuccessor285UsingStack {
  Stack<TreeNode> st = new Stack<>();
  boolean rec(TreeNode node, TreeNode target) {
    st.push(node);
    TreeNode t = node;
    while(t != target && t.left != null) {
      st.push(t.left);
      t = t.left;
    }
    if (st.peek() == target) {
      st.pop();
      return true;
    }
    return false;
  }

  public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
    if (root == null) return null;
    if (p.right != null) {
      rec(p.right, p);
      return st.peek();
    }

    boolean found = rec(root, p);
    while(!found && st.size() > 0) {
      TreeNode top = st.pop();
      if (top.right != null) {
        found = rec(top.right, p);
      }
    }

    if (st.size() == 0) return null;
    return st.peek();
  }
}

