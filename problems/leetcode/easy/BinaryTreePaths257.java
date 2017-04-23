public class BinaryTreePaths257 {
  String buildResultStr(LinkedList<TreeNode> lst) {
    Iterator<TreeNode> iterator = lst.iterator();
    StringBuilder sb = new StringBuilder();
    while(iterator.hasNext()) {
      TreeNode node = iterator.next();
      sb.append(Integer.toString(node.val));
      if (iterator.hasNext()) {
        sb.append("->");
      }
    }
    return sb.toString();
  }
  void dfs(TreeNode node, LinkedList<TreeNode> lst, List<String> acc) {
    lst.addLast(node);
    if (node.left != null) {
      dfs(node.left, lst, acc);
    }
    if (node.right != null) {
      dfs(node.right, lst, acc);
    }
    if (node.left == null && node.right == null) {
      acc.add(buildResultStr(lst));
    }
    lst.pollLast();
  }
  public List<String> binaryTreePaths(TreeNode root) {
    List<String> acc = new LinkedList<>();
    if (root == null) return acc;
    dfs(root, new LinkedList<>(), acc);
    return acc;
  }
}
