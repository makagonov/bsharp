import java.util.*;

public class NumbersChallenge {

  public int MinNumber(int[] s) {
    int n = s.length;
    TreeSet<Integer> possible = new TreeSet<>();
    for (int mask = 0; mask < (1 << n); mask++) {
      int sum = 0;
      for (int i = 0; i < n; i++)
        if ((mask&(1 << i)) > 0) {
          sum += s[i];
        }
      possible.add(sum);
    }
    int lb = 0;
    for (int i : possible) {
      if (i - lb > 1) break;
      else lb = i;
    }
    return lb + 1;
  }

}
