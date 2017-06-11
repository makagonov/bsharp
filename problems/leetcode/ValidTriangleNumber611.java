public class ValidTriangleNumber611 {
  public int triangleNumber(int[] a) {
    if (a == null) return 0;
    int n = a.length;
    if (n < 3) return 0;
    Arrays.sort(a);
    int res = 0;
    for (int i = 0; i < n - 2; i++) {
      for (int j = i + 1; j < n - 1; j++) {
        int min = a[j] - a[i] + 1;
        int max = a[j] + a[i] - 1;
        
        int l = j + 1, r = n - 1;
        while(l < r) {
          int mid = l + (r - l) / 2;
          if (a[mid] < min) {
            l = mid + 1;
          } else {
            r = mid;
          }
        }
        int saveL = l;
        if (a[saveL] < min) continue;
        l = j + 1;
        r = n - 1;
        while(l < r) {
          int mid = r - (r - l) / 2;
          if (a[mid] > max) {
            r = mid - 1;
          } else {
            l = mid;
          }
        }
        if (a[r] > max) continue;
        if (saveL <= r) res += (r - saveL + 1);
      }
    }
    return res;
  }
}

