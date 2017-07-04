public class MedianFinder295 {
  private int size;
  private PriorityQueue<Integer> q1;
  private PriorityQueue<Integer> q2;
  /** initialize your data structure here. */
  public MedianFinder() {
    size = 0;
    q1 = new PriorityQueue<>((a, b) -> a > b ? -1 : 1);
    q2 = new PriorityQueue<>((a, b) -> a < b ? -1 : 1);
  }

  public void addNum(int num) {
    if (q1.isEmpty()) {
      q1.add(num);
    } else if (q1.size() == q2.size()) {
      if (num < q2.peek()) {
        q1.add(num);
      } else {
        q1.add(q2.poll());
        q2.add(num);
      }
    }
    else if (q1.size() > q2.size()) {
      if (num > q1.peek()) {
        q2.add(num);
      } else {
        q2.add(q1.poll());
        q1.add(num);
      }
    }
    size++;
  }

  public double findMedian() {
    if (size % 2 == 1) return q1.peek();
    return (q1.peek() + q2.peek()) / 2.;
  }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */
