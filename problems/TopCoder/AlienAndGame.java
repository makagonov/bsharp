import java.util.*;

public class AlienAndGame {

  public int getNumber(String[] board) {
    int n = board.length, m = board[0].length();
    char [][] arr = new char[n][];
    for (int i = 0; i < n; i++)
      arr[i] = board[i].toCharArray();
    int best = 1;
    for (int i = 0; i < n; i++)
      for (int j = 0; j < m; j++) {
        for (int len = best + 1; i + len <= n && j + len <= m; len++) {
          boolean allSameInRows = true;
          for (int k = i; allSameInRows && k < i + len; k++) {
            for (int f = j + 1; f < j + len; f++) {
              if (arr[k][f] != arr[k][f - 1]) {
                allSameInRows = false;
                break;
              }
            }
          }
          if (allSameInRows && best < len)
            best = len;
        }
      }
        
    return best * best;
  }

}
