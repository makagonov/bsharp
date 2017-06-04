/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class MergeKSortedLists23 {
  public ListNode mergeKLists(ListNode[] lists) {
    int m = lists.length;
    if (m == 0) return null;

    PriorityQueue<ListNode> q = new PriorityQueue<>((a, b) -> a.val - b.val);
    ListNode result = null;
    ListNode cur = null;
    for (ListNode lst : lists) {
      if (lst != null) {
        q.add(lst);
      }
    }
    result = q.peek();

    while(!q.isEmpty()) {
      ListNode min = q.poll();
      if (min.next != null) {
        q.offer(min.next);
        min.next = null;
      }

      if (cur == null) {
        cur = min;
      } else {
        cur.next = min;
        cur = min;
      }
    }
    return result;
  }
}
