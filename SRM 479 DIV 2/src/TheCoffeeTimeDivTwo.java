import java.util.*;
import java.math.*;

import static java.lang.Math.*;

public class TheCoffeeTimeDivTwo {

  public int find(int n, int[] tea) {
    int[] coffee = new int[n - tea.length];
    for (int i = 1, k = 0; i <= n; i++) {
      boolean isTea = false;
      for (int j = 0; j < tea.length; j++)
        if (tea[j] == i) {
          isTea = true;
          break;
        }
      if (!isTea) {
        coffee[k++] = i;
      }
    }
    return solve(tea) + solve(coffee);
  }

  private int solve(int[] a) {
    if (a.length == 0) return 0;
    Arrays.sort(a);
    int size = a.length;
    int result = a.length * 4;
    while (size > 0) {
      result += 47 + a[size - 1] * 2;
      size -= 7;
    }
    return result;
  }
}
