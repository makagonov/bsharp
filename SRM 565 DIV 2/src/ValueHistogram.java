import java.util.*;

public class ValueHistogram {
  public String[] build(int[] values) {
    int[] counts = new int[10];
    for (int i : values)
      counts[i]++;
    int max = 0;
    for (int i : counts)
      if (i > max)
        max = i;

    char[][] arr = new char[max + 1][10];
    for (int i = 0; i < arr.length; i++)
      Arrays.fill(arr[i], '.');

    for (int i = 0; i < 10; i++) {
      for (int j = 0, k = max; j < counts[i]; j++, k--)
        arr[k][i] = 'X';
    }

    String[] ret = new String[arr.length];
    for (int i = 0; i < arr.length; i++)
      ret[i] = new String(arr[i]);
    return ret;
  }
}