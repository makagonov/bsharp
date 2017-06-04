public class MaximalSquare221 {
  public int maximalSquare(char[][] matrix) {
    int n = matrix.length;
    if (n == 0) return 0;
    int m = matrix[0].length;
    if (m == 0) return 0;

    int [][] sum = new int[n][m];
    sum[0][0] = matrix[0][0] - '0';
    for (int i = 1; i < n; i++) {
      sum[i][0] = sum[i - 1][0] + matrix[i][0] - '0';
    }
    for (int i = 1; i < m; i++) {
      sum[0][i] = sum[0][i - 1] + matrix[0][i] - '0';
    }
    for (int i = 1; i < n; i++)
      for (int j = 1; j < m; j++)
        sum[i][j] = sum[i - 1][j] + sum[i][j - 1] - sum[i - 1][j - 1] + matrix[i][j] - '0';

    if (sum[n - 1][m - 1] == 0) return 0; //there are no '1's
    int best = 1;
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        if (matrix[i][j] == '0') continue;
        for (int k = best + 1; i + k <= n && j + k <= m; k++) {
          if (isAllOnesSquare(sum, i, j, k)) {
            best = k;
          }
        }
      }
    }
    return best * best;
  }

  boolean isAllOnesSquare(int [][] sum, int x, int y, int size) {
    if (x + size > sum.length || y + size > sum[0].length) return false;
    int ones = sum[x + size - 1][y + size - 1];
    if (x > 0) ones -= sum[x - 1][y + size - 1];
    if (y > 0) ones -= sum[x + size - 1][y - 1];
    if (x > 0 && y > 0) ones += sum[x - 1][y - 1];
    return ones == size * size;
  }
}
