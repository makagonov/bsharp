public class SumRootToLeafNumbers129 {
  public int sumNumbers(TreeNode root) {
    if (root == null) return 0;
    List<Integer> allPaths = new LinkedList<>();
    dfs(allPaths, root, 0);
    int sum = 0;
    for (int i : allPaths) {
      sum += i;
    }
    return sum;
  }

  void dfs(List<Integer> acc, TreeNode node, int curNum) {
    int val = curNum * 10 + node.val;
    if (node.left != null) dfs(acc, node.left, val);
    if (node.right != null) dfs(acc, node.right, val);
    if (node.left == null && node.right == null) acc.add(val);
  }
}
