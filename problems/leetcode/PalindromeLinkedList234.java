/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class PalindromeLinkedList234 {
  public boolean isPalindrome(ListNode head) {
    int size = getSize(head);
    if (size == 0) return true;
    if (size == 1) return true;
    ListNode middle = getMiddleNode(head, size);
    ListNode tail = reverse(middle);
    while(head != middle) {
      if (head.val != tail.val) return false;
      head = head.next;
      tail = tail.next;
    }
    return true;
  }

  int getSize(ListNode node) {
    if (node == null) return 0;
    int size = 0;
    while(node != null) {
      node = node.next;
      size++;
    }
    return size;
  }

  ListNode getMiddleNode(ListNode node, int size) {
    int pos = size / 2;
    int cur = 0;
    while(cur < pos) {
      node = node.next;
      cur++;
    }
    return node;
  }

  ListNode reverse(ListNode node) {
    ListNode prev = null, cur = node, next = null;
    while(cur != null) {
      next = cur.next;
      cur.next = prev;
      prev = cur;
      cur = next;
    }
    return prev;
  }
}
