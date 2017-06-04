/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class MergeTwoSortedLists21 {
  public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
    if (l1 == null && l2 == null) return null;
    if (l1 == null) return l2;
    if (l2 == null) return l1;

    ListNode result = null, cur = null;

    while(l1 != null || l2 != null) {
      if (l1 == null) {
        cur.next = l2;
        break;
      }
      if (l2 == null) {
        cur.next = l1;
        break;
      }
      ListNode best = null;
      if (l1.val <= l2.val) {
        best = l1;
        l1 = l1.next;
      } else {
        best = l2;
        l2 = l2.next;
      }

      if (cur == null) {
        cur = result = best;
      } else {
        cur.next = best;
        cur = cur.next;
      }
    }
    return result;
  }
}
