public class MaximumSubarray53 {
  public int maxSubArray(int[] a) {
    int n = a.length;
    if (n == 1) return a[0];
    int[] dp = new int[n];
    dp[0] = a[0];
    int best = dp[0];
    for (int i = 1; i < n; i++) {
      dp[i] = Math.max(dp[i - 1] + a[i], a[i]);
      best = Math.max(best, dp[i]);
    }
    return best;
  }
}
