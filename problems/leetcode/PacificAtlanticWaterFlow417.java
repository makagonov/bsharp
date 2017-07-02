public class PacificAtlanticWaterFlow417 {
  private static final int [] dx = new int[]{-1, 0, 1, 0};
  private static final int [] dy = new int[]{0, -1, 0, 1};

  public List<int[]> pacificAtlantic(int[][] matrix) {
    List<int[]> result = new ArrayList<>();
    int n = matrix.length;
    if (n == 0) return result;
    int m = matrix[0].length;
    boolean [][] reachable1 = new boolean[n][m];
    boolean [][] reachable2 = new boolean[n][m];
    for (int i = 0; i < n; i++) {
      dfs(reachable1, matrix, i, 0);
      dfs(reachable2, matrix, i, m - 1);
    }

    for (int j = 0; j < m; j++) {
      dfs(reachable1, matrix, 0, j);
      dfs(reachable2, matrix, n - 1, j);
    }
    for (int i = 0; i < n; i++)
      for(int j = 0; j < m; j++)
        if (reachable1[i][j] && reachable2[i][j])
          result.add(new int []{i, j});

    return result;
  }

  void dfs(boolean [][] used, int [][] matrix, int x, int y) {
    used[x][y] = true;
    for (int i = 0; i < dx.length; i++) {
      int newX = x + dx[i];
      int newY = y + dy[i];
      if (isValidCell(used, newX, newY) && matrix[x][y] <= matrix[newX][newY]) {
        dfs(used, matrix, newX, newY);
      }
    }
  }

  boolean isValidCell(boolean [][] used, int x, int y) {
    if (x < 0 || y < 0 || x >= used.length || y >= used[0].length) return false;
    return !used[x][y];
  }
}
