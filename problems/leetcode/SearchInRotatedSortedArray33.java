public class SearchInRotatedSortedArray33 {
  int find(int [] a, int l, int r, int elem) {
    while(l < r) {
      int mid = l + (r - l) / 2;
      if (a[mid] < elem) l = mid + 1;
      else r = mid;
    }
    return a[l] == elem ? l : -1;
  }

  int findPivot(int [] a) {
    int n = a.length;
    if (a[0] < a[n - 1]) return 0;
    int l = 0, r = n - 1;
    while(l < r) {
      int mid = l + (r - l) / 2;
      if (a[mid] >= a[0]) l = mid + 1;
      else r = mid;
    }
    return r;
  }
  public int search(int[] nums, int target) {
    int n = nums.length;
    if (n == 0) return -1;
    int pivot = findPivot(nums);
    if (pivot == 0) return find(nums, 0, n - 1, target);
    int pos = find(nums, 0, pivot - 1, target);
    if (pos >= 0) return pos;
    return find(nums, pivot, n - 1, target);
  }
}
