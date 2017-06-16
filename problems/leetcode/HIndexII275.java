public class HIndexII275 {
  public int hIndex(int[] a) {
    int n = a.length;
    if (n == 0) return 0;
    if (n == 1) return a[0] == 0 ? 0 : 1;
    int l = 0, r = n;
    while(l < r) {
      int mid = l + (r - l) / 2;
      if (a[mid] < n - mid) {
        l = mid + 1;
      } else {
        r = mid;
      }
    }
    return n - r;
  }
}
