public class TargetSum494 {
  class ResultHolder {
    int value = 0;
  }

  void rec(int [] a, int pos, int curSum, int targetSum, ResultHolder res) {
    if (pos == a.length) {
      if (targetSum == curSum) res.value++;
      return;
    }
    rec(a, pos + 1, curSum + a[pos], targetSum, res);
    rec(a, pos + 1, curSum - a[pos], targetSum, res);
  }

  public int findTargetSumWays(int[] a, int s) {
    ResultHolder res = new ResultHolder();
    rec(a, 1, a[0], s, res);
    rec(a, 1, -a[0], s, res);
    return res.value;
  }
}
