/* The isBadVersion API is defined in the parent class VersionControl.
      boolean isBadVersion(int version); */

public class FirstBadVersion278 extends VersionControl {
  public int firstBadVersion(int n) {
    int l = 1, r = n;
    while(l < r) {
      int mid = l + (r - l) / 2;
      if (!isBadVersion(mid)) {
        l = mid + 1;
      } else {
        r = mid;
      }
    }
    return l;
  }
}
