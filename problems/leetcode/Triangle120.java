public class Triangle120 {
  public int minimumTotal(List<List<Integer>> t) {
    int n = t.size();
    int [][] dp = new int[n][];
    for(int i = 0; i < n; i++) {
      dp[i] = new int[i + 1];
      Arrays.fill(dp[i], Integer.MAX_VALUE / 2);
    }

    int i = 0;
    for (List<Integer> l : t) {
      if (i == 0) {
        dp[i++][0] = l.get(0);
        continue;
      }
      int j = 0;
      for (int val : l) {
        if (j == 0) {
          dp[i][j] = val + dp[i - 1][j];
        } else if (j == i){
          dp[i][j] = val + dp[i - 1][j - 1];
        } else {
          dp[i][j] = val + Math.min(dp[i - 1][j - 1], dp[i - 1][j]);
        }
        j++;
      }
      i++;
    }
    int res = dp[n - 1][0];
    for (i = 1; i < dp[n - 1].length; i++) {
      res = Math.min(res, dp[n - 1][i]);
    }

    return res;
  }
}
