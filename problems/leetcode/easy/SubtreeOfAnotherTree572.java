/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class SubtreeOfAnotherTree572 {
  void findCandidates(List<TreeNode> candidates, TreeNode t, int val) {
    if (t == null) {
      return;
    }
    if (t.val == val) {
      candidates.add(t);
    }
    findCandidates(candidates, t.left, val);
    findCandidates(candidates, t.right, val);
  }

  boolean areSame(TreeNode a, TreeNode b) {
    if (a == null) return b == null;
    if (b == null) return a == null;
    if (a.val != b.val) return false;
    return areSame(a.left, b.left) && areSame(a.right, b.right);
  }

  public boolean isSubtree(TreeNode s, TreeNode t) {
    if (t == null) return true;
    List<TreeNode> candidates = new LinkedList<>();
    findCandidates(candidates, s, t.val);
    for (TreeNode c : candidates) {
      if (areSame(c, t)) return true;
    }
    return false;
  }
}
