import java.util.*;

public class WinterAndCandies {

  public int getNumber(int[] type) {
    int [] f = new int[51];
    for (int t : type) f[t]++;
    int result = 0;
    for (int k = 1; k <= 50; k++) {
      int resK = 1;
      for (int i = 1; i <= k; i++)
        resK *= f[i];
      result += resK;
    }
    return result;
  }
}
