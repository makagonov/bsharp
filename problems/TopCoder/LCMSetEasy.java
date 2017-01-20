import java.util.*;

public class LCMSetEasy {

  public String include(int[] s, int x) {
    Set<Integer> numbers = new HashSet<>();
    Map<Integer, Integer> xFactorization = primeFactorization(x);
    for (int i : s) {
      Map<Integer, Integer> iFactorization = primeFactorization(i);
      boolean good = true;
      //Note: this for loop is actually equivalent to checking if x % i == 0 :)
      for (Map.Entry<Integer, Integer> entry : iFactorization.entrySet()) {
        if (xFactorization.getOrDefault(entry.getKey(), 0) < entry.getValue()) {
          good = false;
          break;
        }
      }
      if (good) {
        numbers.add(i);
      }
    }
    int lcm = 1;
    for (int i : numbers) {
      lcm = lcm(lcm, i);
    }
    return lcm == x ? "Possible" : "Impossible";

  }

  public int lcm(int a, int b) {
    return a / gcd(a, b) * b;
  }

  int gcd(int a, int b) {
    if (b == 0) return a;
    return gcd(b, a % b);
  }


  Map<Integer, Integer> primeFactorization(int a) {
    Map<Integer, Integer> factors = new HashMap<>();
    for (int i = 2; i * i <= a; i++) {
      while(a % i == 0) {
        int cur = factors.getOrDefault(i, 0);
        factors.put(i, cur + 1);
        a /= i;
      }
    }
    if (a > 1) {
      factors.put(a, 1);
    }
    return factors;
  }

}
