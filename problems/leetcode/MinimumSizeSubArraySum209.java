public class Solution {
  public int minSubArrayLen(int s, int[] a) {
    int n = a.length;
    if (n == 0) return 0;
    for (int i : a)
      if (i >= s)
        return 1;
    int best = 0;
    int l = 0, r = 0, sum = a[0];
    while (true) {
      if (sum >= s) {
        if (best == 0 || r - l + 1 < best) {
          best = r - l + 1;
        }
        sum -= a[l++];
      } else if (sum < s) {
        r++;
        if (r == n) break;
        sum += a[r];
      }
    }
    return best;
  }
}
