/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */

import java.util.*;

public class ReorderList143 {
  public void reorderList(ListNode head) {
    if (head == null || head.next == null) return;
    Stack<ListNode> stack = new Stack<>();
    Queue<ListNode> queue = new LinkedList<>();
    for (ListNode cur = head; cur != null; cur = cur.next) {
      queue.add(cur);
      stack.push(cur);
    }
    ListNode q = queue.poll();
    ListNode s = stack.pop();
    for (int i = 0; ; i ^= 1) {
      if (q == s) {
        q.next = null;
        break;
      }
      if (i == 0) {
        q.next = s;
        q = queue.poll();
      } else {
        s.next = q;
        s = stack.pop();
      }
    }
  }
}
