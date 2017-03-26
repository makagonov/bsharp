public class WordSearch79 {
  final int [] dx = new int []{0, 1, 0, -1};
  final int [] dy = new int []{1, 0, -1, 0};

  boolean rec(char [][] b, boolean [][] used, String w, int pos, int x, int y) {
    if (pos == w.length()) return true;
    if (x < 0 || y < 0 || x >= b.length || y >= b[0].length) return false;
    if (used[x][y] || b[x][y] != w.charAt(pos)) return false;
    used[x][y] = true;
    boolean success = false;
    for (int i = 0; i < 4 && !success; i++) {
      success |= rec(b, used, w, pos + 1, x + dx[i], y + dy[i]);
    }
    used[x][y] = false;
    return success;
  }
  public boolean exist(char[][] b, String w) {
    boolean [][] used = new boolean[b.length][b[0].length];
    for (int i = 0; i < b.length; i++) {
      for (int j = 0; j < b[0].length; j++) {
        boolean success = rec(b, used, w, 0, i, j);
        if (success) return true;
      }
    }
    return false;
  }
}
