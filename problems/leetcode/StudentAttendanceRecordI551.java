public class StudentAttendanceRecordI551 {
  public boolean checkRecord(String s) {
    int curLate = 0;
    int absentCount = 0;
    for (int i = 0; i < s.length(); i++) {
      char c = s.charAt(i);
      if (c == 'L') {
        curLate++;
        if (curLate > 2) return false;
      } else {
        curLate = 0;
        if (c == 'A') {
          absentCount++;
          if (absentCount > 1) return false;
        }

      }
    }
    return true;
  }
}
