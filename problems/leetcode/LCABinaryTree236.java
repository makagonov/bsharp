import java.util.*;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

public class LCABinaryTree236 {
	//START OF RECURSIVE SOLUTION
	public TreeNode lowestCommonAncestorRecursive(TreeNode root, TreeNode p, TreeNode q) {
		Map<TreeNode, Integer> levels = new HashMap<>();
		LinkedList<TreeNode> order = new LinkedList<>();
		dfs(root, order, levels, 0);
		TreeNode lca = null;
		int lowestLevel = Integer.MAX_VALUE;
		boolean foundP = false, foundQ = false;		
		for (TreeNode node : order) {
			if (foundP && foundQ) break;
			if (node == p) foundP = true;
			if (node == q) foundQ = true;
			if (foundP || foundQ) {
				int nodeLevel = levels.get(node);
				if (nodeLevel < lowestLevel) {
					lowestLevel = nodeLevel;
					lca = node;
				}
			}
		}
		return lca;
	}

	void dfs(TreeNode node, LinkedList<TreeNode> order, Map<TreeNode, Integer> levels, int level) {
		levels.put(node, level);
		order.add(node);
		if (node.left != null) {
			dfs(node.left, order, levels, level + 1);
			order.add(node);
		}
		if (node.right != null) {
			dfs(node.right, order, levels, level + 1);
			order.add(node);
		}
	}
	//END OF RECURSIVE SOLUTION
	
	
	//START OF ITERATIVE SOLUTION
	class State {
		int level;
		TreeNode node;
		public State(TreeNode node, int level) { this.node = node; this.level = level; }
	}

	public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
		Map<TreeNode, Integer> levels = new HashMap<>();
		LinkedList<TreeNode> order = new LinkedList<>();
		HashSet<TreeNode> used = new HashSet<>();
		Stack<State> stack = new Stack<>();
		stack.push(new State(root, 0));
		while(!stack.isEmpty()) {
			State state = stack.peek();
			TreeNode node = state.node;
			used.add(node);
			order.add(node);
			levels.put(node, state.level);
			if (node.left != null && !used.contains(node.left)) {
				stack.push(new State(node.left, state.level + 1));
				continue;
			}
			if (node.right != null && !used.contains(node.right)) {
				stack.push(new State(node.right, state.level + 1));
				continue;
			}
			stack.pop();
		}

		TreeNode lca = null;
		int lowestLevel = Integer.MAX_VALUE;
		boolean foundP = false, foundQ = false;		
		for (TreeNode node : order) {
			if (foundP && foundQ) break;
			if (node == p) foundP = true;
			if (node == q) foundQ = true;
			if (foundP || foundQ) {
				int nodeLevel = levels.get(node);
				if (nodeLevel < lowestLevel) {
					lowestLevel = nodeLevel;
					lca = node;
				}
			}
		}
		return lca;
	}
	//END OF ITERATIVE SOLUTION

	public static void main (String [] args) {
		TreeNode root = new TreeNode(3, 
				new TreeNode(5, 
						new TreeNode(6, null, null),
						new TreeNode(2, 
								new TreeNode(7, null, null),
								new TreeNode(4, null, null))
						),
				new TreeNode(1,
						new TreeNode(0, null, null),
						new TreeNode(8, null, null))
				);

		System.out.println(new LCABinaryTree236().lowestCommonAncestor(root, root.left.right.left, root.left.left).val);
	}


}