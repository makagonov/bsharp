public class FindLeavesOfBinaryTree366 {
  class NodeWrapper {
    NodeWrapper parent;
    TreeNode node;
    int children = 0;
  }

  void rec(List<NodeWrapper> acc, TreeNode node, NodeWrapper parent) {
    NodeWrapper wrapper = new NodeWrapper();
    wrapper.node = node;
    wrapper.parent = parent;
    if (node.left != null) {
      wrapper.children++;
      rec(acc, node.left, wrapper);
    }
    if (node.right != null) {
      wrapper.children++;
      rec(acc, node.right, wrapper);
    }
    acc.add(wrapper);
  }

  public List<List<Integer>> findLeaves(TreeNode root) {
    List<List<Integer>> res = new LinkedList<>();
    if (root == null) return res;

    Set<NodeWrapper> [] sets = new LinkedHashSet[3];
    for (int i = 0; i < 3; i++)
      sets[i] = new LinkedHashSet<>();

    List<NodeWrapper> all = new LinkedList<>();
    rec(all, root, null);

    for (NodeWrapper wrapper : all) {
      sets[wrapper.children].add(wrapper);
    }
    while(sets[0].size() > 0) {
      List<Integer> level = new LinkedList<>();
      Map<NodeWrapper, Integer> changed = new HashMap<>();
      for (NodeWrapper wrapper : sets[0]) {
        level.add(wrapper.node.val);
        if (wrapper.parent != null) {
          changed.putIfAbsent(wrapper.parent, wrapper.parent.children);
          wrapper.parent.children--;
        }
      }
      res.add(level);
      sets[0].clear();
      for (Map.Entry<NodeWrapper, Integer> item : changed.entrySet()) {
        sets[item.getValue()].remove(item.getKey());
        sets[item.getKey().children].add(item.getKey());
      }
    }

    return res;
  }
}
