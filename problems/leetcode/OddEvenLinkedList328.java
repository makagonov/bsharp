/**
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode(int x) { val = x; }
 * }
 */
public class OddEvenLinkedList328 {
  public ListNode oddEvenList(ListNode head) {
    if (head == null) return null;
    ListNode evenHead = head.next;
    if (evenHead == null) return head;

    ListNode cur = head;
    ListNode b = null;
    ListNode c = null;
    while (cur != null) {
      b = cur.next;
      c = b == null ? null : b.next;

      cur.next = c;
      cur = b;
    }

    ListNode oddTail = head;
    while (oddTail.next != null)
      oddTail = oddTail.next;
    oddTail.next = evenHead;
    return head;
  }
}
