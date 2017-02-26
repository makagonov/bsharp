import java.util.*;

public class Solution {
  public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
    if (intervals.size() == 0) return Arrays.asList(newInterval);

    TreeSet<Interval> set = new TreeSet<>((a, b) -> a.start - b.start);
    for (Interval i : intervals)
      set.add(i);
    Interval toInsert = newInterval;
    while (true) {
      Interval floor;
      Interval ceil;
      if (intersect((floor = set.floor(toInsert)), newInterval)) {
        set.remove(floor);
        toInsert = merge(floor, newInterval);
      } else if (intersect(toInsert, (ceil = set.ceiling(toInsert)))) {
        set.remove(ceil);
        toInsert = merge(toInsert, ceil);
      } else {
        break;
      }
    }
    set.add(toInsert);
    List<Interval> result = new ArrayList<>();
    while (!set.isEmpty()) {
      result.add(set.pollFirst());
    }
    return result;
  }

  Interval merge(Interval a, Interval b) {
    return new Interval(Math.min(a.start, b.start), Math.max(a.end, b.end));
  }

  boolean intersect(Interval a, Interval b) {
    if (a == null || b == null) return false;
    if (b.start >= a.start && b.start <= a.end) return true;
    if (b.end >= a.start && b.end <= a.start) return true;
    return false;
  }
}
