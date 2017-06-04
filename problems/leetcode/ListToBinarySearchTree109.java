/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Sorted ListToBinarySearchTree109 {
  class CurNodeHolder {
    ListNode cur;
    CurNodeHolder(ListNode node) {
      cur = node;
    }
    void move() {
      cur = cur.next;
    }

    int val() {
      return cur.val;
    }
  }
  private int getListSize(ListNode head) {
    if (head == null) return 0;
    int size = 0;
    ListNode cur = head;
    while(cur != null) {
      size++;
      cur = cur.next;
    }
    return size;
  }

  public TreeNode sortedListToBST(ListNode head) {
    int size = getListSize(head);
    if (size == 0) return null;
    return buildTree(new CurNodeHolder(head), 0, size - 1);
  }

  TreeNode buildTree(CurNodeHolder holder, int start, int end) {
    if (start > end) return null;
    int mid = start + (end - start) / 2;
    TreeNode left = buildTree(holder, start, mid - 1);
    TreeNode ret = new TreeNode(holder.val());
    holder.move();
    ret.left = left;
    ret.right = buildTree(holder, mid + 1, end);
    return ret;
  }
}
