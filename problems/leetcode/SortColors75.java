public class Solution {
  public void sortColors(int[] a) {
    int [] counts = new int[3];
    for (int i : a)
      counts[i]++;
    int p = 0;
    for (int i = 0; i < 3; i++)
      for (int j = 0; j < counts[i]; j++)
        a[p++] = i;
  }
}
