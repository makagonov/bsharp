public class BSTIterator173 {
  Stack<TreeNode> st;
  public BSTIterator(TreeNode root) {
    st = new Stack<>();
    if (root != null) {
      populateStack(root);
    }
  }

  void populateStack(TreeNode root) {
    st.push(root);
    TreeNode node = root;
    while(node.left != null) {
      node = node.left;
      st.push(node);
    }
  }

  public boolean hasNext() {
    return st.size() > 0;
  }

  public int next() {
    TreeNode top = st.pop();
    if (top.right != null) {
      populateStack(top.right);
    }
    return top.val;
  }
}

