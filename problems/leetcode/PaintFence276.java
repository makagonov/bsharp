public class PaintFence276 {
  public int numWays(int n, int k) {
    if (n == 0 || k == 0) return 0;
    if (n == 1) return k;
    int[] dp = new int[n + 1];
    dp[1] = 1;
    dp[2] = k;
    for (int i = 3; i <= n; i++) {
      dp[i] = (k - 1) * (dp[i - 1] + dp[i - 2]);
    }
    return dp[n] * k;
  }
}
