public class Solution {
  public int[][] multiply(int[][] a, int[][] b) {
    int n = a.length, m = b[0].length;
    HashSet<Integer>[] bIndexes = new HashSet[m];
    for (int j = 0; j < m; j++) {
      bIndexes[j] = new HashSet<>();
      for (int i = 0; i < b.length; i++) {
        if (b[i][j] != 0)
          bIndexes[j].add(i);
      }
    }
    int[][] ret = new int[n][m];
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        int sum = 0;
        for (int idx : bIndexes[j]) {
          sum += a[i][idx] * b[idx][j];
        }
        ret[i][j] = sum;
      }
    }
    return ret;
  }
}
