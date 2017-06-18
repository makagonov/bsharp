/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class BoundaryOfBinaryTree545 {
  private static enum Boundary { LEFT, RIGHT };

  void dfsBoundary(TreeNode node, Boundary boundary, LinkedList<TreeNode> nodes) {
    if (node == null) return;
    if (boundary == Boundary.LEFT) {
      nodes.addLast(node);
      if (node.left != null) dfsBoundary(node.left, boundary, nodes);
      else dfsBoundary(node.right, boundary, nodes);
    } else if (boundary == Boundary.RIGHT) {
      nodes.addFirst(node);
      if (node.right != null) dfsBoundary(node.right, boundary, nodes);
      else dfsBoundary(node.left, boundary, nodes);
    }
  }

  private LinkedList<TreeNode> getBoundary(TreeNode root, Boundary boundary) {
    LinkedList<TreeNode> res = new LinkedList<>();
    if (root == null) return res;
    dfsBoundary(root, boundary, res);
    return res;
  }

  void dfsLeaves(TreeNode node, LinkedList<TreeNode> leaves) {
    if (node == null) return;
    if (node.left == null && node.right == null) {
      leaves.add(node);
      return;
    }
    dfsLeaves(node.left, leaves);
    dfsLeaves(node.right, leaves);
  }

  LinkedList<TreeNode> getLeaves(TreeNode root) {
    LinkedList<TreeNode> leaves = new LinkedList<>();
    dfsLeaves(root, leaves);
    return leaves;
  }

  public List<Integer> boundaryOfBinaryTree(TreeNode root) {
    List<Integer> res = new LinkedList<>();
    if (root == null) return res;
    LinkedList<TreeNode> leftBoundary = getBoundary(root.left, Boundary.LEFT);
    LinkedList<TreeNode> rightBoundary = getBoundary(root.right, Boundary.RIGHT);
    LinkedList<TreeNode> leaves = getLeaves(root);
    LinkedHashSet<TreeNode> resSet = new LinkedHashSet<>();
    resSet.add(root);
    resSet.addAll(leftBoundary);
    resSet.addAll(leaves);
    resSet.addAll(rightBoundary);
    for (TreeNode node : resSet) {
      res.add(node.val);
    }
    return res;
  }
}
