public class FirstMissingPositive41 {
  public int firstMissingPositive(int[] nums) {
    int n = nums.length;
    boolean [] used = new boolean[n + 2];
    for (int i = 0; i < n; i++) {
      if (nums[i] > n || nums[i] <= 0) continue;
      used[nums[i]] = true;
    }
    for (int i = 1; i < used.length; i++) {
      if (!used[i])
        return i;
    }
    return -1;
  }
}
