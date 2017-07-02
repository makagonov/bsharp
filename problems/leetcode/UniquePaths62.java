public class UniquePaths62 {
  public int uniquePaths(int m, int n) {
    if (n == 0 || m == 0) return 0;
    int [][] dp = new int[n][m];
    dp[0][0] = 1;
    for (int i = 1; i < n; i++) {
      dp[i][0] += dp[i - 1][0];
    }
    for (int j = 1; j < m; j++) {
      dp[0][j] += dp[0][j - 1];
    }
    for (int i = 1; i < n; i++)
      for (int j = 1; j < m; j++)
        dp[i][j] += dp[i - 1][j] + dp[i][j - 1];

    return dp[n - 1][m - 1];
  }
}
