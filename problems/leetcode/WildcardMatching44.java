public class WildcardMatching44 {
  public boolean isMatch(String s, String p) {
    int n = s.length(), m = p.length();
    if (m == 0) return n == 0;
    boolean [][] dp = new boolean[n + 1][m + 1];
    dp[0][0] = true;
    for (int i = 0; i < p.length() && p.charAt(i) == '*'; i++) {
      dp[0][i + 1] = true;
    }

    for (int i = 1; i <= n; i++) {
      char c1 = s.charAt(i - 1);
      for (int j = 1; j <= m; j++) {
        char c2 = p.charAt(j - 1);
        if (c2 == '?') {
          dp[i][j] = dp[i - 1][j - 1];
        } else  if (c2 == '*') {
          dp[i][j] = dp[i - 1][j] || dp[i][j - 1] || dp[i - 1][j - 1];
        } else {
          if (c1 == c2) dp[i][j] = dp[i - 1][j - 1];
        }
      }
    }
    return dp[n][m];
  }
}
