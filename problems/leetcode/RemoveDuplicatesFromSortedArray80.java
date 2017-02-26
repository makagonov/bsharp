public class Solution {
  public int removeDuplicates(int[] a) {
    int n = a.length;
    if (n <= 2) return n;
    int cur = a[0];
    int cnt = 1;
    int res = 0;
    int idx = 1;
    for (int i = 1; i < n; i++) {
      if (a[i] == a[i - 1]) {
        cnt++;
        if (cnt <= 2) {
          a[idx++] = a[i];
        }
      } else {
        res += Math.min(2, cnt);
        cur = a[i];
        cnt = 1;
        a[idx++] = a[i];
      }
    }
    res += Math.min(cnt, 2);
    return res;
  }
}
