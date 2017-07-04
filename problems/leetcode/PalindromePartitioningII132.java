public class PalindromePartitioningII132 {
  private static final int oo = Integer.MAX_VALUE / 2;
  public int minCut(String s) {
    int n = s.length();
    int [][] memo = new int[n][n];
    for (int i = 0; i < n; i++) {
      Arrays.fill(memo[i], oo);
    }
    return rec(s, 0, n - 1, memo, palindromTable(s));
  }

  boolean [][] palindromTable(String str) {
    int n = str.length();
    boolean [][] palin = new boolean[n][n];
    for (int i = 0; i < n; i++)
      palin[i][i] = true;
    char [] s = str.toCharArray();
    for (int i = 1; i < n; i++) {
      if (s[i] == s[i - 1]) palin[i - 1][i] = true;
      for (int j = i - 2; j >= 0; j--) {
        if (s[i] == s[j] && palin[j + 1][i - 1]) {
          palin[j][i] = true;
        }
      }
    }
    return palin;
  }

  int rec(String s, int start, int end, int [][] memo, boolean [][] isPalin) {
    if (memo[start][end] != oo) return memo[start][end];
    int minPossible = 0;
    if (isPalin[start][end]) return memo[start][end] = minPossible;
    minPossible++;
    // means we need to split the string somewhere
    for (int i = start; i < end; i++) {
      if (isPalin[start][i] && isPalin[i + 1][end]) {
        return memo[start][end] = minPossible;
      }
    }
    minPossible++;
    for (int i = start; i < end; i++) {
      int minSplitsLeft = rec(s, start, i, memo, isPalin);
      int minSplitsRight = rec(s, i + 1, end, memo, isPalin);
      memo[start][end] = Math.min(memo[start][end], 1 + minSplitsLeft + minSplitsRight);
      if (memo[start][end] == minPossible) break;
    }
    return memo[start][end];
  }
}
