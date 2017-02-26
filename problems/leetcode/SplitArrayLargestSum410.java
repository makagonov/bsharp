public class Solution {
  final long oo = Long.MAX_VALUE;

  public int splitArray(int[] a, int m) {
    int n = a.length;
    long[][] dp = new long[n + 1][n + 1];
    fill(dp, oo);
    dp[n][0] = 0;
    int totalSum = sum(a, 0);
    int max = 0;
    for (int i = 0, j = n - 1; i < n; i++) {
      dp[i][1] = totalSum;
      totalSum -= a[i];

      max = Math.max(max, a[i]);
      dp[j][n - j] = max;
    }
    return (int) solve(a, dp, 0, m);
  }

  void fill(long[][] a, long val) {
    for (int i = 0; i < a.length; i++)
      Arrays.fill(a[i], val);
  }

  int sum(int[] a, int start) {
    int s = 0;
    for (int i = start; i < a.length; i++)
      s += a[i];
    return s;
  }

  int max(int[] a, int start) {
    int max = 0;
    for (int i : a)
      if (i > max)
        max = i;
    return max;
  }

  long solve(int[] a, long[][] dp, int start, int m) {
    if (dp[start][m] < oo) return dp[start][m];
    if (a.length - start < m) {
      return oo;
    }
    int prefixSum = 0;
    long best = oo;
    for (int i = start; i <= a.length - m; i++) {
      prefixSum += a[i];
      long curMax = Math.max(prefixSum, solve(a, dp, i + 1, m - 1));
      if (curMax < best)
        best = curMax;
    }
    dp[start][m] = best;
    return best;
  }
}
