/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class FindModeInBinarySearchTree501 {
  class ModsHolder {
    List<Integer> mods;
    int count;

    public ModsHolder() {
      this.mods = new LinkedList<>();
      this.count = 0;
    }
    void offer(int val, int count) {
      if (count < this.count) return;
      if (count == this.count) this.mods.add(val);
      if (count > this.count) {
        this.mods.clear();
        this.mods.add(val);
        this.count = count;
      }
    }
  }

  void rec(TreeNode node, Map<Integer, Integer> counts) {
    if (node == null) return;
    counts.put(node.val, counts.getOrDefault(node.val, 0) + 1);
    rec(node.left, counts);
    rec(node.right, counts);
  }

  int [] listToArray(List<Integer> lst) {
    int [] ret = new int[lst.size()];
    int idx = 0;
    for (int i : lst) {
      ret[idx++] = i;
    }
    return ret;
  }

  public int[] findMode(TreeNode root) {
    if (root == null) return new int[0];
    ModsHolder holder = new ModsHolder();
    Map<Integer, Integer> counts = new HashMap<>();
    rec(root, counts);
    for (Map.Entry<Integer, Integer> entry : counts.entrySet()) {
      holder.offer(entry.getKey(), entry.getValue());
    }
    return listToArray(holder.mods);
  }
}
