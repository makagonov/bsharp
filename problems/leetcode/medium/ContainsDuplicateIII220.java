public class ContainsDuplicateIII220 {

  public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
    int n = nums.length;
    if (k == 0 || n <= 1) return false;
    TreeSet<Long> cache = new TreeSet<>();
    for (int i = 0; i < n; i++) {
      long val = (long)nums[i];
      Long floor = cache.floor(val);
      if (floor != null && val - floor <= t) return true;
      Long ceiling = cache.ceiling(val);
      if (ceiling != null && ceiling - val <= t) return true;
      if (i - k >= 0) {
        cache.remove((long)nums[i - k]);
      }
      cache.add(val);
    }
    return false;
  }
}
