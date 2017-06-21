public class Matrix542 {
  class Cell implements Comparable<Cell> {
    int x, y, dist;
    public Cell(int x, int y, int dist) {
      this.x = x;
      this.y = y;
      this.dist = dist;
    }

    @Override
    public int compareTo(Cell other) {
      return this.dist - other.dist;
    }
  }

  private static final int [] dx = new int []{-1, 0, 1, 0};
  private static final int [] dy = new int []{0, -1, 0, 1};

  public int[][] updateMatrix(int[][] matrix) {
    int n = matrix.length;
    if (n == 0) return matrix;
    int m = matrix[0].length;

    PriorityQueue<Cell> q = new PriorityQueue<>();
    int [][] d = new int[n][m];
    for (int i = 0; i < n; i++) {
      Arrays.fill(d[i], Integer.MAX_VALUE / 2);
    }

    Cell [][] cells = new Cell[n][m];

    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        cells[i][j] = new Cell(i, j, d[i][j]);
        if (matrix[i][j] == 0) {
          d[i][j] = cells[i][j].dist = 0;
          q.add(cells[i][j]);
        }
      }
    }

    while(!q.isEmpty()) {
      Cell cell = q.poll();
      for (int i = 0; i < dx.length; i++) {
        int x = dx[i] + cell.x, y = dy[i] + cell.y, dist = cell.dist + 1;
        if (canAdd(d, x, y, dist)) {
          d[x][y] = dist;
          cells[x][y].dist = dist;
          q.add(cells[x][y]);
        }
      }
    }
    return d;
  }

  boolean canAdd(int [][] d, int x, int y, int dist) {
    if (x < 0 || y < 0 || x >= d.length || y >= d[0].length)
      return false;
    return d[x][y] > dist;
  }
}
