public class LongestPalindromicSubstring5 {
  public String longestPalindrome(String str) {
    int n = str.length();
    boolean [][] palin = new boolean[n][n];
    for (int i = 0; i < n; i++)
      palin[i][i] = true;
    char [] s = str.toCharArray();
    for (int i = 1; i < n; i++) {
      if (s[i] == s[i - 1]) palin[i - 1][i] = true;
      for (int j = i - 2; j >= 0; j--) {
        if (s[i] == s[j] && palin[j + 1][i - 1]) {
          palin[j][i] = true;
        }
      }
    }
    for (int len = n; len > 0; len--) {
      for (int i = 0; i + len <= n; i++) {
        if (palin[i][i + len - 1]) {
          return str.substring(i, i + len);
        }
      }
    }
    return "";
  }
}
