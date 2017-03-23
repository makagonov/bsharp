public class OneEditDistance161 {
  public boolean isOneEditDistance(String s, String t) {
    int n = s.length(), m = t.length();
    if (n < m) return isOneEditDistance(t, s);
    if (n - m > 1) return false;
    if (n == m) {
      int diffCount = 0;
      for (int i = 0; i < n; i++)
        if (s.charAt(i) != t.charAt(i))
          diffCount++;
      return diffCount == 1;
    }

    int pS = 0, pT = 0;
    while(pS < n && pT < m) {
      if (s.charAt(pS) == t.charAt(pT)) {
        pS++;
        pT++;
      } else {
        pS++;
      }
    }
    return pT == m;
  }
}
