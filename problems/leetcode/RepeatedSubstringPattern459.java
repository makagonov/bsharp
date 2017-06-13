public class RepeatedSubstringPattern459 {
  public boolean repeatedSubstringPattern(String s) {
    int n = s.length();
    int [] p = new int[n];
    for (int i = 1; i < p.length; i++) {
      int j = p[i - 1];
      while(j > 0 && s.charAt(j) != s.charAt(i)) {
        j = p[j - 1];
      }
      if (s.charAt(j) == s.charAt(i)) {
        j++;
      }
      p[i] = j;
    }

    int patLen = n - p[n - 1];
    return patLen != n && n % patLen == 0;
  }
}

