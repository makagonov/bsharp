public class LongestPalindromicSubsequence516 {
  public int longestPalindromeSubseq(String s) {
    int n = s.length();
    if (n == 0) return 0;
    int [][] memo = new int[n][n];
    rec(memo, s.toCharArray(), 0, n - 1);
    return memo[0][n - 1];
  }

  int rec(int [][] memo, char [] s, int i, int j) {
    if (i > j) return 0;
    if (memo[i][j] > 0) return memo[i][j];
    if (i == j) {
      return memo[i][j] = 1;
    }
    int res = 1;
    if (s[i] == s[j]) {
      res = rec(memo, s, i + 1, j - 1) + 2;
    } else {
      res = Math.max(rec(memo, s, i + 1, j), rec(memo, s, i, j - 1));
    }
    memo[i][j] = res;
    return res;
  }
}
