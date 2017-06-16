public class MaximalRectangle85 {
  public int maximalRectangle(char[][] matrix) {
    int n = matrix.length;
    if (n == 0) return 0;
    int m = matrix[0].length;
    int [][] dp = new int[n][m];
    for (int i = 0; i < n; i++) {
      dp[i][0] = matrix[i][0] - '0';
    }
    for (int i = 0; i < n; i++) {
      for (int j = 1; j < m; j++) {
        dp[i][j] = matrix[i][j] == '0' ? 0 : dp[i][j - 1] + 1;
      }
    }
    int best = 0;
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        if (dp[i][j] == 0) continue;
        int curWidth = Integer.MAX_VALUE, curHeight = 1;
        for (int k = i; k >= 0; k--, curHeight++) {
          if (dp[k][j] == 0) break;
          curWidth = Math.min(curWidth, dp[k][j]);
          int curBest = curWidth * curHeight;
          if (curBest > best) {
            best = curBest;
          }
        }
      }
    }
    return best;
  }
}
