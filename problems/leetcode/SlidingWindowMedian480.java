public class  SlidingWindowMedian480 {

  public double[] medianSlidingWindow(int[] nums, int k) {
    MedianFinder finder = new MedianFinder();
    int n = nums.length;
    double [] res = new double[n - k + 1];
    Item [] items = new Item[n];
    for (int i = 0; i < k - 1; i++) {
      items[i] = finder.add(i, nums[i]);
    }
    for (int i = k - 1, j = 0; i < n; i++, j++) {
      items[i] = finder.add(i, nums[i]);
      res[j] = finder.getMedian();
      finder.remove(items[j]);
    }
    return res;
  }

  class Item implements Comparable<Item> {
    int pos;
    long val;
    Item(int p, int v) {
      this.pos = p;
      this.val = v;
    }

    @Override
    public int compareTo(Item other) {
      if (this.val < other.val) return -1;
      if (this.val > other.val) return 1;
      return this.pos - other.pos;
    }

    @Override
    public String toString() {
      return String.format("%d", this.val);
    }
  }

  class MedianFinder {
    private TreeSet<Item> q1;
    private TreeSet<Item> q2;
    MedianFinder() {
      q1 = new TreeSet<>();
      q2 = new TreeSet<>();
    }

    private void add(Item item) {
      if (q1.isEmpty()) {
        q1.add(item);
      } else if (q1.size() == q2.size()) {
        if (item.val < q2.first().val) {
          q1.add(item);
        } else {
          q1.add(q2.pollFirst());
          q2.add(item);
        }
      }
      else if (q1.size() > q2.size()) {
        if (item.val > q1.last().val) {
          q2.add(item);
        } else {
          q2.add(q1.pollLast());
          q1.add(item);
        }
      }
    }

    Item add(int pos, int num) {
      Item item = new Item(pos, num);
      add(item);
      return item;
    }

    public int size() {
      return q1.size() + q2.size();
    }

    void remove(Item item) {
      if (q1.remove(item)) {
        if (q2.size() > 0) add(q2.pollFirst());
      } else {
        q2.remove(item);
        add(q1.pollLast());
      }
    }

    double getMedian() {
      if (this.size() % 2 == 1) return q1.last().val;
      return (q1.last().val + q2.first().val) / 2.;
    }
  }
}
