public class SingleNumber136 {
  public int singleNumber(int[] a) {
    int n = a.length;
    if (n == 1) return a[0];
    int val = a[0];
    for (int i = 1; i < n; i++) {
      val ^= a[i];
    }

    return val;
  }
}
