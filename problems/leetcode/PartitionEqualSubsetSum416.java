public class PartitionEqualSubsetSum416 {
  public boolean canPartition(int[] nums) {
    int n = nums.length;
    if (n == 0) return false;

    int sum = 0;
    for (int i : nums)
      sum += i;
    if (sum % 2 > 0) return false;
    int target = sum / 2;

    boolean[][] dp = new boolean[2][target + 1];
    dp[0][0] = true;
    int cur = 1;
    for (int i = 1; i <= n; i++, cur = 1 - cur) {
      int item = nums[i - 1];
      for (int j = 0; j <= target; j++) {
        if (dp[1 - cur][j])
          dp[cur][j] = true;
        else if (j - item >= 0 && dp[1 - cur][j - item]) {
          dp[cur][j] = true;
        }
      }
    }
    return dp[1 - cur][target];
  }
}
