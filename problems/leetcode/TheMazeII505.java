public class TheMazeII505 {
  private static final int [] dx = new int[]{-1, 0, 1, 0};
  private static final int [] dy = new int[]{0, -1, 0, 1};
  private static final int oo = Integer.MAX_VALUE / 2;

  class Cell implements Comparable<Cell> {
    int x, y, dist;
    Cell(int x, int y, int dist) {
      this.x = x;
      this.y = y;
      this.dist = dist;
    }

    @Override
    public int compareTo(Cell other) {
      if (this.dist < other.dist) return -1;
      return 1;
    }
  }

  public int shortestDistance(int[][] m, int[] start, int[] destination) {
    boolean [][] maze = getEnhancedMaze(m);
    Cell [][] cells = new Cell[maze.length][maze[0].length];
    for (int i = 0; i < cells.length; i++) {
      for (int j = 0; j < cells[0].length; j++) {
        cells[i][j] = new Cell(i, j, oo);
      }
    }
    Cell startCell = cells[start[0] + 1][start[1] + 1];
    startCell.dist = 0;
    Cell destCell = cells[destination[0] + 1][destination[1] + 1];

    PriorityQueue<Cell> q = new PriorityQueue<>();
    q.add(startCell);

    while(!q.isEmpty()) {
      Cell cell = q.poll();
      for (int i = 0; i < dx.length; i++) {
        Cell nextCell = moveBall(cells, maze, cell, dx[i], dy[i]);
        int cellsDistance = getDistanceBetweenCells(cell, nextCell);
        if (nextCell.dist > cell.dist + cellsDistance) {
          nextCell.dist = cell.dist + cellsDistance;
          q.add(nextCell);
        }
      }
    }

    return destCell.dist == oo ? -1 : destCell.dist;
  }

  Cell moveBall(Cell [][] cells, boolean [][] maze, Cell initCell, int xInc, int yInc) {
    int x = initCell.x, y = initCell.y;
    while(!maze[x + xInc][y + yInc]) {
      x += xInc;
      y += yInc;
    }
    return cells[x][y];
  }

  int getDistanceBetweenCells(Cell from, Cell to) {
    return Math.abs(from.x - to.x) + Math.abs(from.y - to.y);
  }

  private boolean [][] getEnhancedMaze(int [][] mz) {
    int n = mz.length + 2, m = mz[0].length + 2;
    boolean [][] maze = new boolean[n][m];
    for (int i = 0; i < n; i++) {
      maze[i][0] = maze[i][m - 1] = true;
    }
    for (int i = 0; i < m; i++) {
      maze[0][i] = maze[n - 1][i] = true;
    }

    for (int i = 1; i <= mz.length; i++) {
      for (int j = 1; j <= mz[0].length; j++) {
        maze[i][j] = mz[i - 1][j - 1] == 1;
      }
    }
    return maze;
  }
}
