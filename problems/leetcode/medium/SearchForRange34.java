public class SearchForRange34 {
  static final int [] NOT_FOUND = new int []{-1, -1};
  public int[] searchRange(int[] arr, int target) {
    if (arr.length == 0) return NOT_FOUND;
    int left = leftBorder(arr, target);
    if (left < 0) return NOT_FOUND;
    int right = rightBorder(arr, target);
    return new int []{left, right};
  }

  int rightBorder(int [] a, int t) {
    int l = 0, r = a.length - 1;
    while(l < r) {
      int mid = r - (r - l) / 2;
      if (a[mid] > t) {
        r = mid - 1;
      } else {
        l = mid;
      }
    }
    return a[r] == t ? r : -1;
  }

  int leftBorder(int [] a, int t) {
    int l = 0, r = a.length - 1;
    while(l < r) {
      int mid = l + (r - l) / 2;
      if (a[mid] < t) {
        l = mid + 1;
      } else {
        r = mid;
      }
    }
    return a[l] == t ? l : -1;
  }
}
