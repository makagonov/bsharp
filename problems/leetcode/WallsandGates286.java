public class WallsandGates286 {
  final int INF = Integer.MAX_VALUE;
  final int [] dx = new int []{-1, 0, 1, 0};
  final int [] dy = new int []{0, 1, 0, -1};

  public void wallsAndGates(int[][] rooms) {
    int n = rooms.length;
    if (n == 0) return;
    int m = rooms[0].length;
    Queue<int[]> q = new LinkedList<>();
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        if (rooms[i][j] == 0) {
          q.add(new int []{i, j, 0});
          while(!q.isEmpty()) {
            int [] t = q.poll();
            int tx = t[0], ty = t[1], td = t[2];
            for (int k = 0; k < 4; k++) {
              int newx = tx + dx[k];
              int newy = ty + dy[k];
              if (isValid(rooms, newx, newy) && rooms[newx][newy] > td + 1) {
                rooms[newx][newy] = td + 1;
                q.add(new int []{newx, newy, td + 1});
              }
            }
          }
        }
      }
    }
  }

  boolean isValid(int [][] r, int x, int y) {
    if (x < 0 || y < 0 || x >= r.length || y >= r[0].length || r[x][y] == -1)
      return false;
    return true;
  }
}
