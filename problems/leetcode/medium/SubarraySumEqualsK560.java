public class SubarraySumEqualsK560 {
  public int subarraySum(int[] nums, int k) {
    int runningSum = 0;
    int n = nums.length;
    Map<Integer, Integer> memo = new HashMap<>();
    memo.put(0, 1);
    int count = 0;
    for (int i = 0; i < n; i++) {
      runningSum += nums[i];
      if (memo.containsKey(runningSum - k)) {
        count += memo.get(runningSum - k);
      }
      memo.put(runningSum, memo.getOrDefault(runningSum, 0) + 1);
    }
    return count;
  }
}

