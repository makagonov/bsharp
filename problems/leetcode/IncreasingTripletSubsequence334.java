public class IncreasingTripletSubsequence334 {
  public boolean increasingTriplet(int[] nums) {
    int n = nums.length;
    if (n <= 2) return false;
    int [] dp = new int[n];
    Arrays.fill(dp, 1);
    for (int i = 1; i < n; i++) {
      for (int j = 0; j < i; j++) {
        if (nums[i] > nums[j]) {
          dp[i] = Math.max(dp[i], dp[j] + 1);
        }
      }
    }
    for (int i : dp) {
      if (i >= 3) {
        return true;
      }
    }
    return false;
  }
}
