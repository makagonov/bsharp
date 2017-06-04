public class ContainsDuplicationsII219 {
  public boolean containsNearbyDuplicate(int[] nums, int k) {
    int n = nums.length;
    if (k <= 0 || n == 0) return false;
    Set<Integer> set = new HashSet<>();
    for (int i = 0; i < Math.min(k, n); i++) {
      if (!set.add(nums[i]))
        return true;
    }

    for (int i = k; i < n; i++) {
      if (!set.add(nums[i])) return true;
      set.remove(nums[i - k]);
    }
    return false;
  }
}
