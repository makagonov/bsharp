public class Sqrt69 {
  public int mySqrt(int x) {
    if (x == 0) return 0;
    if (x == 1) return 1;
    double l = 0, r = x, eps = 1e-3;
    while(r - l > eps) {
      double mid = l + (r - l) / 2;
      if (mid * mid < x) l = mid;
      else r = mid;
    }
    int res = (int)Math.round(l);
    int square = res * res;
    if (square > x || square < 0) res--;
    return res;
  }
}
