import java.util.*;

public class LCMSet {

  public String equal(int[] A, int[] B) {
    boolean ok = check(A, B) && check(B, A);
    if (ok) return "Equal";
    return "Not equal";
  }

  boolean check(int [] A, int [] B) {
    for (int x : A) {
      int lcm = 1;
      for (int i : B) {
        if (x % i == 0)
          lcm = lcm(lcm, i);
      }
      if (lcm != x) {
        return false;
      }
    }
    return true;
  }

  int lcm(int a, int b) {
    return a / gcd(a, b) * b;
  }

  int gcd(int a, int b) {
    if (b == 0) return a;
    return gcd(b, a % b);
  }
}
