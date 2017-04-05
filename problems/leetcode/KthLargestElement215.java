public class KthLargestElement215 {
  public int findKthLargest(int[] a, int k) {
    PriorityQueue<Integer> pq = new PriorityQueue<>();
    for (int i = 0; i < k; i++)
      pq.add(a[i]);

    for (int i = k; i < a.length; i++) {
      int top = pq.peek();
      if (a[i] > top) {
        pq.poll();
        pq.add(a[i]);
      }
    }
    return pq.peek();
  }
}

