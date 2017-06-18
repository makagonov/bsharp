public class DeleteOperationForTwoStrings583 {
  public int minDistance(String word1, String word2) {
    int n = word1.length(), m = word2.length();
    if (n == 0 || m == 0) return Math.max(n, m);
    int [][] dp = new int[n][m];

    for (int i = 0, j = 0; i < n; i++) {
      if (word1.charAt(i) == word2.charAt(0)) {
        j = 1;
      }
      dp[i][0] = j;
    }
    for (int i = 0, j = 0; i < m; i++) {
      if (word2.charAt(i) == word1.charAt(0)) {
        j = 1;
      }
      dp[0][i] = j;
    }
    for (int i = 1; i < n; i++) {
      for (int j = 1; j < m; j++) {
        dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
        if (word1.charAt(i) == word2.charAt(j)) {
          dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - 1] + 1);
        }
      }
    }
    int lcs = dp[n - 1][m - 1];
    return n + m - 2 * lcs;
  }
}
