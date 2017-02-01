import java.util.*;
public class MeetingRooms253 {
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

  public int minMeetingRooms(Interval[] intervals) {
    int n = intervals.length;
    if (n <= 1) return n;
    Arrays.sort(intervals, (a, b) -> a.start != b.start ? a.start - b.start : a.end - b.end);
    //array that stores the last interval for the room
    Interval [] rooms = new Interval[n];
    rooms[0] = intervals[0];
    
    for (int i = 1; i < n; i++) {
      for (int j = 0; j < n; j++) {
        if (!intersect(rooms[j], intervals[i])) {
          rooms[j] = intervals[i];
          break;
        }
      }
    }
    
    int roomsCount = 0;
    for (; roomsCount < n; roomsCount++)
      if (rooms[roomsCount] == null)
        break;
    
    return roomsCount;
  }
  
  private boolean intersect(Interval prev, Interval cur) {
    if (prev == null) return false;
    if (cur.start == prev.start && cur.end == prev.end)
      return true;
    if (cur.start > prev.start && cur.start < prev.end)
      return true;
    if (prev.end > cur.start && prev.end < cur.end)
      return true;
    return false;
  }
  
  public static void main (String [] args) {
    System.out.println(new MeetingRooms253().minMeetingRooms(new Interval[]{
        new Interval(0, 30),
        new Interval(5, 10),
        new Interval(15, 20)
        }));
  }
}