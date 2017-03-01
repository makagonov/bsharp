public class Solution {
  public boolean isMatch(String s, String p) {
    String[] pat = preparePattern(p);
    int n = s.length(), m = pat.length;
    if (m == 0) return n == 0;
    boolean[][] dp = new boolean[n + 1][m + 1];
    dp[0][0] = true;
    for (int i = 1; i <= m; i++) {
      if (pat[i - 1].length() > 1) {
        dp[0][i] = true;
      } else {
        break;
      }
    }

    for (int i = 1; i <= n; i++) {
      char sChar = s.charAt(i - 1);
      for (int j = 1; j <= m; j++) {
        char pChar = pat[j - 1].charAt(0);
        if (pat[j - 1].length() == 2) {
          if (pChar == '.' || pChar == sChar) {
            dp[i][j] = dp[i - 1][j] || dp[i][j - 1] || dp[i - 1][j - 1];
          } else {
            dp[i][j] = dp[i][j - 1];
          }
        } else if (sChar == pChar || pChar == '.') {
          dp[i][j] = dp[i - 1][j - 1];
        }
      }
    }
    return dp[n][m];
  }

  String[] preparePattern(String p) {
    List<String> result = new ArrayList<>();
    int n = p.length();
    if (n == 0) return result.toArray(new String[0]);
    for (int i = 0; i < n - 1; i++) {
      if (p.charAt(i + 1) == '*') {
        result.add("" + p.charAt(i) + p.charAt(i + 1));
        i++;
      } else {
        result.add("" + p.charAt(i));
      }
    }
    char lastChar = p.charAt(n - 1);
    if (lastChar != '*') {
      result.add("" + p.charAt(n - 1));
    }
    return result.toArray(new String[0]);
  }
}
