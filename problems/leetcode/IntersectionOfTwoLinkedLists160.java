/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class IntersectionOfTwoLinkedLists160 {
  int getListSize(ListNode node) {
    if (node == null) return 0;
    int size = 0;
    while(node != null) {
      size++;
      node = node.next;
    }
    return size;
  }
  ListNode move(int numSteps, ListNode node) {
    for (int i = 0; i < numSteps; i++) {
      node = node.next;
    }
    return node;
  }
  public ListNode getIntersectionNode(ListNode a, ListNode b) {
    int sizeA = getListSize(a), sizeB = getListSize(b);
    if (sizeA == 0 || sizeB == 0) return null;
    if (sizeA > sizeB) a = move(sizeA - sizeB, a);
    else if (sizeB > sizeA) b = move(sizeB - sizeA, b);
    while(a != b) {
      a = a.next;
      b = b.next;
    }
    return a;
  }
}
