public class MeetingRooms252 {
  public boolean canAttendMeetings(Interval[] intervals) {
    int n = intervals.length;
    if (n <= 1) return true;
    Arrays.sort(intervals, (a, b) -> a.end - b.end);
    for (int i = 1; i < n; i++)
      if (intervals[i].start < intervals[i - 1].end)
        return false;
    return true;
  }
}
