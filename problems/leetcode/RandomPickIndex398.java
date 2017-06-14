public class RandomPickIndex398 {
  final Random rnd;
  final Integer [] indexes;
  final int [] nums;
  final int n;
  public Solution(int[] nums) {
    this.nums = nums;
    this.n = nums.length;
    this.rnd = new Random();
    this.indexes = new Integer[n];
    for (int i = 0; i < n; i++) indexes[i] = i;
    Arrays.sort(indexes, (a, b) -> {
      if (nums[a] < nums[b]) return -1;
      if (nums[a] > nums[b]) return 1;
      return 0;
    });
    Arrays.sort(nums);
  }

  public int pick(int target) {
    int l = 0, r = n - 1;
    while(l < r) {
      int mid = l + (r - l) / 2;
      if (nums[mid] < target) {
        l = mid + 1;
      } else {
        r = mid;
      }
    }
    int from = l;
    l = 0;
    r = n - 1;
    while(l < r) {
      int mid = r - (r - l) / 2;
      if (nums[mid] > target) {
        r = mid - 1;
      } else {
        l = mid;
      }
    }

    int randomPos = from + rnd.nextInt(r - from + 1);
    return indexes[randomPos];
  }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(nums);
 * int param_1 = obj.pick(target);
 */
