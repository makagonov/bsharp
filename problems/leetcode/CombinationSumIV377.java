public class CombinationSumIV377 {
  public int combinationSum4(int[] nums, int target) {
    return rec(nums, new HashMap<>(), target);
  }

  int rec(int [] a, Map<Integer, Integer> memo, int targetSum) {
    if (targetSum < 0) return 0;
    if (targetSum == 0) return 1;
    if (memo.containsKey(targetSum)) {
      return memo.get(targetSum);
    }
    int res = 0;
    for (int i = 0; i < a.length; i++) {
      res += rec(a, memo, targetSum - a[i]);
    }
    memo.put(targetSum, res);
    return res;
  }
}
