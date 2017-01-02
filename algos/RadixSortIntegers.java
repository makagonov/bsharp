import java.util.Arrays;

public class RadixSortIntegers {
  public static void sort(int [] a) {
    int min = Integer.MAX_VALUE;
    for (int i : a)
      if (i < min)
        min = i;
    if (min >= 0) {
      sortNonNegative(a);
      return;
    }
    //if there are negative numbers in the given array
    //then simply adding the minimum in the array to each element
    //obtaining array where the minimum element is 0
    for (int i = 0; i < a.length; i++)
      a[i] -= min;
    sortNonNegative(a);
    //reverting transformed values back
    for (int i = 0; i < a.length; i++)
      a[i] += min;
  }
  
  private static void sortNonNegative(int [] a) {
    int numDigits = getMaxNumDigits(a);
    for (int divisor = 1, digit = 0; digit < numDigits; digit++, divisor *= 10) {
      sortByDigit(a, divisor);
    }
  }

  private static int getDigitAt(int val, int divisor) {
    return val / divisor % 10;
  }

  private static int getMaxNumDigits(int [] a) {
    int max = Integer.MIN_VALUE;
    for (int i : a)
      if (i > max)
        max = i;
    int d = 1;
    for (long i = 9; ; i = i * 10 + 9, d++) {
      if(i >= max)
        break;
    }
    return d;
  }

  private static void sortByDigit(int [] a, int divisor) {
    int n = a.length;
    int [] counts = new int[10];
    for (int i : a)
      counts[getDigitAt(i, divisor)]++;
    for (int i = 1; i < 10; i++)
      counts[i] += counts[i - 1];

    int [] sorted = new int [n + 1];
    for (int i = n - 1; i >= 0; i--) {
      int digit = getDigitAt(a[i], divisor);

      sorted[counts[digit]] = a[i];
      counts[digit]--;
    }
    for (int i = 1; i <= n; i++)
      a[i - 1] = sorted[i];
  }
  
  public static void main (String [] args) {
    int [] a = new int []{170, -45, 75, 90, -802, 24, 2, 66};
    RadixSortIntegers.sort(a);
    System.out.println(Arrays.toString(a)); //[-802, -45, 2, 24, 66, 75, 90, 170]
  }
}
