import java.util.*;
public class MergeIntervals56 {
  static class Interval {
    int start;
    int end;
    Interval() { start = 0; end = 0; }
    Interval(int s, int e) { start = s; end = e; }
    @Override
    public String toString() {
      return String.format("[%d,%d]", start, end);
    }
  }

  public List<Interval> merge(List<Interval> intervals) {
    if (intervals.size() <= 1) return intervals;
    Collections.sort(intervals, (a, b) -> a.start != b.start ? a.start - b.start : a.end - b.end);
    List<Interval> result = new ArrayList<>();
    for (int i = 1; i < intervals.size(); i++) {
      Interval prev = intervals.get(i  - 1);
      Interval cur = intervals.get(i);
       if (intersect(prev, cur)) {
         cur.start = prev.start;
         if (prev.end > cur.end) 
           cur.end = prev.end;
       } else {
         result.add(prev);
       }
    }
    result.add(intervals.get(intervals.size() - 1));
    return result;
  }
  
  private boolean intersect(Interval prev, Interval cur) {
    return cur.start >= prev.start && cur.start <= prev.end;
  }
  
  public static void main (String [] args) {
    System.out.println(new MergeIntervals56().merge(Arrays.asList(
        new Interval(1, 3),
        new Interval(2, 6),
        new Interval(8, 10),
        new Interval(15, 18)
        )));
  }
}