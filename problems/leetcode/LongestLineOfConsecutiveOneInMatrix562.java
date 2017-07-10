public class LongestLineOfConsecutiveOneInMatrix562 {
  public int longestLine(int[][] mt) {
    int n = mt.length;
    if (n == 0) return 0;
    int m = mt[0].length;
    int [][][] dp = new int[n][m][4];
    int [] dx = new int []{0, -1, -1, -1};
    int [] dy = new int []{-1, -1, 0, 1};
    int best = 0;
    for (int i = 0; i < n; i++) {
      for(int j = 0; j < m; j++) {
        if (mt[i][j] == 0) continue;
        for (int k = 0; k < 4; k++) {
          dp[i][j][k] = 1;
          int x = i + dx[k], y = j + dy[k];
          if (x >= 0 && x < n && y >= 0 && y < m) {
            dp[i][j][k] += dp[x][y][k];
          }
          best = Math.max(best, dp[i][j][k]);
        }
      }
    }
    return best;
  }
}
