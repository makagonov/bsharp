public class LIS300 {
  public int lengthOfLIS(int[] nums) {
    int n = nums.length;
    if (n == 0) return 0;
    int [] dp = new int[n];
    Arrays.fill(dp, 1);
    for (int i = 1; i < n; i++)
      for (int j = 0; j < i; j++)
        if (nums[j] < nums[i])
          dp[i] = Math.max(dp[i], dp[j] + 1);

    int max = 1;
    for (int i : dp) max = Math.max(max, i);
    return max;
  }
}
