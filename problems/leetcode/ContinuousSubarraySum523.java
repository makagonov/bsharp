public class ContinuousSubarraySum523 {

  public boolean checkSubarraySum(int[] nums, int k) {
    Map<Integer, Integer> memo = new HashMap<>();
    memo.put(0, -1);
    int n = nums.length;
    int runningSum = 0;
    for (int i = 0; i < n; i++) {
      runningSum += nums[i];
      if (k != 0) runningSum %= k;
      Integer pos = memo.get(runningSum);
      if (pos != null) {
        if (i - pos > 1) return true;
      } else {
        memo.put(runningSum, i);
      }
    }
    return false;
  }
}
