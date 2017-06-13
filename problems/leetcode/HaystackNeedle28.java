public class HaystackNeedle28 {
  public int strStr(String haystack, String needle) {
    int t = haystack.length(), n = needle.length();
    if (n == 0) return 0;
    String combined = needle + "#" + haystack;
    int [] p = new int[combined.length()];
    for (int i = 1; i < p.length; i++) {
      int j = p[i - 1];
      while(j > 0 && combined.charAt(j) != combined.charAt(i)) {
        j = p[j - 1];
      }
      if (combined.charAt(j) == combined.charAt(i)) {
        j++;
      }
      p[i] = j;
      if (p[i] == n) {
        return i - 2 * n;
      }
    }
    return -1;
  }
}
