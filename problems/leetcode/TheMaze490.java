public class TheMaze490 {
  private static final int [] dx = new int[]{-1, 0, 1, 0};
  private static final int [] dy = new int[]{0, -1, 0, 1};



  public boolean hasPath(int[][] m, int[] start, int[] destination) {
    int [] newStart = new int[]{start[0] + 1, start[1] + 1};
    int [] newDest = new int[]{destination[0] + 1, destination[1] + 1};

    boolean [][] maze = getEnhancedMaze(m);
    Queue<int []> q = new LinkedList<>();
    q.add(newStart);

    boolean [][] used = new boolean[maze.length][maze[0].length];
    while(!q.isEmpty()) {
      int [] pos = q.poll();
      if (pos[0] == newDest[0] && pos[1] == newDest[1]) {
        return true;
      }
      if (isUsed(used, pos)) {
        continue;
      }
      markUsed(used, pos);
      for (int i = 0; i < dx.length; i++) {
        int [] nextPos = moveBall(maze, pos, dx[i], dy[i]);
        if (!isUsed(used, nextPos)) {
          q.add(nextPos);
        }
      }
    }
    return false;
  }

  int [] moveBall(boolean [][] maze, int [] pos, int xInc, int yInc) {
    int x = pos[0], y = pos[1];
    while(!maze[x + xInc][y + yInc]) {
      x += xInc;
      y += yInc;
    }
    return new int []{x, y};
  }

  boolean isUsed(boolean [][] used, int [] pos) {
    return used[pos[0]][pos[1]];
  }

  void markUsed(boolean [][] used, int [] pos) {
    used[pos[0]][pos[1]] = true;
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
