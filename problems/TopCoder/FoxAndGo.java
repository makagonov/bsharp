import java.util.*;

public class FoxAndGo {
  final int CONST = 31;

  int [] dx = new int []{-1, 0, 1, 0};
  int [] dy = new int []{0, -1, 0, 1};

  public int maxKill(String[] b) {
    int n = b.length, m = b[0].length();
    char [][] board = new char[n][];
    for (int i = 0; i < n; i++)
      board[i] = b[i].toCharArray();
    int best = 0;
    for (int x = 0; x < n; x++)
      for (int y = 0; y < m; y++) {
        if (board[x][y] != '.')
          continue;
        
        board[x][y] = 'x';
        int resultForBoard = getBestForBoard(board, n, m);
        if (best < resultForBoard) {
          best = resultForBoard;
        }
        board[x][y] = '.';
      }
    return best;
  }

  int getBestForBoard(char [][] board, int n, int m) {
    int result = 0;
    boolean [][] used = new boolean[n][m];
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        if (!used[i][j] && board[i][j] == 'o') {
          HashSet<Integer> whites = new HashSet<>();
          HashSet<Integer> neighboringDots = new HashSet<>();
          Queue<Integer> q = new LinkedList<>();
          addPoint(i, j, used, q, whites);
          while(!q.isEmpty()) {
            int point = q.poll();
            int [] coords = fromPoint(point);
            for (int k = 0; k < dx.length; k++) {
              int newX = coords[0] + dx[k];
              int newY = coords[1] + dy[k];
              if (isValid(used, newX, newY) && board[newX][newY] == 'o') {
                addPoint(newX, newY, used, q, whites);
              }
            }
          }
          
          for (int point : whites) {
            int [] coords = fromPoint(point);
            for (int k = 0; k < dx.length; k++) {
              int newX = coords[0] + dx[k];
              int newY = coords[1] + dy[k];
              if (isValid(used, newX, newY) && board[newX][newY] == '.') {
                neighboringDots.add(toPoint(newX, newY));
              }
            }
          }

          if (neighboringDots.size() == 0) {
            result += whites.size();
          }
        }
      }
    }
    return result;
  }
  
  void addPoint(int x, int y, boolean [][] used, Queue<Integer> q, HashSet<Integer> whites) {
    used[x][y] = true;
    int point = toPoint(x, y);
    q.add(point);
    whites.add(point);
  }

  int toPoint(int x, int y) {
    return x * CONST + y;
  }

  int [] fromPoint(int val) {
    return new int []{val / CONST, val % CONST};
  }

  boolean isValid(boolean [][] used, int x, int y) {
    if (x < 0 || y < 0 || x >= used.length || y >= used[0].length || used[x][y])
      return false;
    return true;
  }

}
