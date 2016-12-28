import java.util.*;

public class SplitIntoPairs {

  public int makepairs(int[] a, int x) {
    int n = a.length,
        numNegative = 0;

    long largestNegative = Integer.MIN_VALUE,
        smallestNonNegative = Integer.MAX_VALUE;

    for (int i = 0; i < n; i++) {
      if (a[i] < 0) {
        numNegative++;
        if (a[i] > largestNegative)
          largestNegative = a[i];
      } else if (a[i] < smallestNonNegative) {
        smallestNonNegative = a[i];
      }
    }

    if (numNegative % 2 == 0) return n / 2;
    if (smallestNonNegative * largestNegative >= x)
      return n / 2;
    return n / 2 - 1;
  }

}
