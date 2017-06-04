/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class SortedArrayToBinarySearchTree108 {
  public TreeNode sortedArrayToBST(int[] nums) {
    return buildTree(nums, 0, nums.length - 1);
  }

  private TreeNode buildTree(int [] a, int l, int r) {
    if (l > r) return null;
    if (l == r) return new TreeNode(a[l]);
    int mid = l + (r - l) / 2;
    TreeNode ret = new TreeNode(a[mid]);
    ret.left = buildTree(a, l, mid - 1);
    ret.right = buildTree(a, mid + 1, r);
    return ret;
  }
}
