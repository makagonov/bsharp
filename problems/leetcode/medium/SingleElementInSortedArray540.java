public class SingleElementInSortedArray540 {
  public int singleNonDuplicate(int[] nums) {
    return find(nums, 0, nums.length - 1);
  }

  int find(int [] a, int lo, int hi) {
    if (lo == hi) return a[lo];
    int halfSize = (hi - lo + 1) / 2;

    int mid = lo + (hi - lo) / 2;
    if (a[mid] != a[mid - 1] && a[mid] != a[mid + 1])
      return a[mid];
    if (halfSize % 2 == 0) {
      if (a[mid - 1] == a[mid]) return find(a, lo, mid - 2);
      return find(a, mid + 2, hi);
    }
    if (a[mid] == a[mid - 1]) return find(a, mid + 1, hi);
    return find(a, lo, mid - 1);
  }
}
