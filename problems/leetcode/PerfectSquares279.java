public class PerfectSquares279 {
  public int numSquares(int n) {
    List<Integer> squares = new ArrayList<>();
    for (int i = 1; i * i <= n; i++) {
      squares.add(i * i);
    }
    int [] memo = new int[n + 1];
    Arrays.fill(memo, -1);
    memo[0] = 0;
    return find(memo, squares, n);
  }

  int find(int [] memo, List<Integer> squares, int n) {
    if (memo[n] >= 0) return memo[n];
    int res = Integer.MAX_VALUE;

    for (int i : squares) {
      if (i > n) break;
      res = Math.min(res, 1 + find(memo, squares, n - i));
    }
    return memo[n] = res;
  }
}
