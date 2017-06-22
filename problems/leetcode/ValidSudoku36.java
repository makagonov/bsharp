public class ValidSudoku36 {
  public boolean isValidSudoku(char[][] board) {
    for (int i = 0; i < 9; i++) {
      boolean [] used = new boolean[10];
      for (int j = 0; j < 9; j++) {
        if (!offer(used, board[i][j])) {
          return false;
        }
      }
    }

    for (int i = 0; i < 9; i++) {
      boolean [] used = new boolean[10];
      for (int j = 0; j < 9; j++) {
        if (!offer(used, board[j][i])) {
          return false;
        }
      }
    }

    for (int i = 0; i + 2 <= 9; i += 3) {
      for (int j = 0; j + 2 <= 9; j += 3) {
        boolean [] used = new boolean[10];
        for (int k = i; k <= i + 2; k++) {
          for (int f = j; f <= j + 2; f++) {
            if (!offer(used, board[k][f])) {
              return false;
            }
          }
        }
      }
    }

    return true;
  }

  boolean offer(boolean [] used, char c) {
    if (c == '.') return true;
    int val = c - '0';
    if (used[val]) return false;
    used[val] = true;
    return true;
  }
}
