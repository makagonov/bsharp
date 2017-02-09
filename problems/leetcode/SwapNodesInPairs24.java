/**
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode(int x) { val = x; }
 * }
 */
public class SwapNodesInPairs24 {
  public ListNode swapPairs(ListNode head) {
    if (head == null || head.next == null) return head;
    ListNode newHead = head.next;
    ListNode beforeCur = null;
    ListNode cur = head;
    while (cur != null && cur.next != null) {

      ListNode b = cur.next;
      ListNode c = b.next;
      if (beforeCur != null)
        beforeCur.next = b;
      b.next = cur;
      cur.next = c;
      beforeCur = cur;
      cur = c;
    }
    return newHead;
  }
}
