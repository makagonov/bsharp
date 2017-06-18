public class MaxConsecutiveOnes485 {
  public int findMaxConsecutiveOnes(int[] nums) {
    int cur = 0;
    int best = 0;
    for (int i = 0; i < nums.length; i++) {
      if (nums[i] == 1) {
        cur++;
      } else {
        best = Math.max(best, cur);
        cur = 0;
      }
    }
    return Math.max(best, cur);
  }
}
