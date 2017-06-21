public class MissingRanges163 {
  class Interval {
    int start;
    int end;

    Interval(int s, int e) {
      this.start = s;
      this.end = e;
    }

    @Override
    public String toString() {
      if (start == end) return Integer.toString(start);
      return String.format("%d->%d", start, end);
    }
  }

  public List<String> intervalsToResult(List<Interval> intervals) {
    List<String> res = new LinkedList<>();
    for (Interval i : intervals) {
      res.add(i.toString());
    }
    return res;
  }

  public List<String> findMissingRanges(int[] nums, int lower, int upper) {
    int n = nums.length;
    LinkedList<Interval> intervals = new LinkedList<>();
    if (n == 0) {
      intervals.add(new Interval(lower, upper));
      return intervalsToResult(intervals);
    }

    if (lower < nums[0]) {
      intervals.add(new Interval(lower, nums[0] - 1));
    }

    int cur = nums[0];
    for (int i = 1; i < n; i++) {
      if (nums[i] == nums[i - 1]) continue;
      if (nums[i] - 1 > cur) {
        intervals.addLast(new Interval(cur + 1, nums[i] - 1));
      }
      cur = nums[i];
    }

    if (upper > cur) {
      intervals.add(new Interval(cur + 1, upper));
    }
    return intervalsToResult(intervals);

  }
}
