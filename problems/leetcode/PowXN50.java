public class PowXN50 {
  public double myPow(double x, int n) {
    return binPow(x, (long)n);
  }

  double binPow(double x, long n) {
    if (x == 0) return 1.;
    if (n == 0) return 1.;
    if (n == 1) return x;
    if (n < 0) return 1. / binPow(x, -n);
    if (n % 2 == 1) return x * binPow(x, n - 1);
    double res = binPow(x, n / 2);
    return res * res;
  }
}

