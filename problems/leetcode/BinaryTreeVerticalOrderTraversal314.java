/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
  class Node {
    int level;
    TreeNode node;

    Node(TreeNode n, int l) {
      node = n;
      level = l;
    }
  }

  public List<List<Integer>> verticalOrder(TreeNode root) {
    List<List<Integer>> result = new ArrayList<>();
    if (root == null) return result;

    Map<Integer, List<Integer>> levels = new HashMap<>();
    Queue<Node> q = new LinkedList<>();
    q.add(new Node(root, 0));
    while (!q.isEmpty()) {
      Node node = q.poll();
      List<Integer> siblings = levels.getOrDefault(node.level, new LinkedList<>());
      siblings.add(node.node.val);
      levels.put(node.level, siblings);
      if (node.node.left != null) {
        q.add(new Node(node.node.left, node.level - 1));
      }
      if (node.node.right != null) {
        q.add(new Node(node.node.right, node.level + 1));
      }
    }
    int minLevel = 0;
    for (int i : levels.keySet())
      if (i < minLevel)
        minLevel = i;
    for (int l = minLevel; levels.containsKey(l); l++) {
      result.add(levels.get(l));
    }
    return result;
  }
}
