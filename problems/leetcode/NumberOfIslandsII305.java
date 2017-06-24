public class NumberOfIslandsII305 {
  private final Random rnd = new Random();
  private static final int [] dx = new int []{-1, 0, 1, 0};
  private static final int [] dy = new int []{0, 1, 0, -1};
  public List<Integer> numIslands2(int n, int m, int[][] positions) {
    List<Integer> res = new ArrayList<>();
    if (n == 0 || m == 0) return res;

    int [] p = new int[n * m];
    int [][] grid = new int[n][m];
    Set<Integer> sets = new HashSet<>();
    for (int [] pos : positions) {
      int x = pos[0], y = pos[1];
      int newSet = encode(m, x, y);
      p[newSet] = newSet;
      grid[x][y] = 1;
      for (int i = 0; i < 4; i++) {
        int xx = x + dx[i], yy = y + dy[i];

        if (xx >= 0 && xx < n && yy >= 0 && yy < m && grid[xx][yy] == 1) {
          int neighborEncoded = encode(m, xx, yy);
          int neighborSet = findSet(p, neighborEncoded);
          p[neighborSet] = newSet;
          sets.remove(neighborSet);
        }
      }
      sets.add(newSet);
      res.add(sets.size());
    }
    return res;
  }

  int encode(int m, int x, int y) {
    return x * m + y;
  }

  int findSet(int [] p, int x) {
    if (x == p[x]) return x;
    return p[x] = findSet(p, p[x]);
  }
}
