import java.util.*;

public class BigFatInteger2 {

  public String isDivisible(int A, int B, int C, int D) {
    TreeMap<Integer, Long> fA = factorsConsideringPower(A, B);
    TreeMap<Integer, Long> fB = factorsConsideringPower(C, D);
    for (Map.Entry<Integer, Long> entry : fB.entrySet()) {
      if (!fA.containsKey(entry.getKey()) || fA.get(entry.getKey()) < entry.getValue()) {
        return getResultVal(false);
      }
    }
    return getResultVal(true);
  }
  
  String getResultVal(boolean res) {
    return res ? "divisible" : "not divisible"; 
  }

  TreeMap<Integer, Long> factorsConsideringPower(int n, int power) {
    TreeMap<Integer, Long> ret = new TreeMap<>();
    for (int i = 2; i * i <= n; i++) {
      if (n % i == 0) {
        int count = 0;
        while(n % i == 0) {
          n /= i;
          count++;
        }
        ret.put(i, 1L * count * power);
      }
    }
    if (n > 1)
      ret.put(n, 1L * power);
    return ret;
  }

}
