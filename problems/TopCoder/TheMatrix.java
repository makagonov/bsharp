import java.util.*;

public class TheMatrix {
  private int n;
  private int m;
  private boolean [][] board;
  
  public int MaxArea(String[] b) {
    n = b.length;
    m = b[0].length();
    board = new boolean[n][m];
    for (int i = 0; i < n; i++)
      for (int j = 0; j  < m; j++)
        board[i][j] = b[i].charAt(j) == '1';

    int best = 0;
    for (int i = 0; i < n; i++) {
      for (int j = 0;j < m; j++) {
        int cornerResult = getResultForTopLeftCorner(i, j);
        if (cornerResult > best) best = cornerResult;
      }
    }

    return best;
  }
  
  private int getResultForTopLeftCorner(int startRow, int startCol) {
    int [][] sum = new int[n][m];
    sum[startRow][startCol] = 1;
    int best = 1;

    for (int x = startRow + 1; x < n; x++)
      if (isPositionValid(startRow, startCol, x, startCol))
        sum[x][startCol] = sum[x - 1][startCol] + 1;
    
    for (int y = startCol + 1; y < m; y++)
      if (isPositionValid(startRow, startCol, startRow, y))
        sum[startRow][y] = sum[startRow][y - 1] + 1;
    
    for (int x = startRow + 1; x < n; x++) {
      for (int y = startCol + 1; y < m; y++) {
        if (isPositionValid(startRow, startCol, x, y) && 
            sum[x - 1][y] > 0 && sum[x][y - 1] > 0 && sum[x - 1][y - 1] > 0) {
          sum[x][y] = sum[x - 1][y] + sum[x][y - 1] - sum[x - 1][y - 1] + 1;
        }
      }
    }
    
    for (int x = startRow; x < n; x++)
      for (int y = startCol; y < m; y++)
        if (sum[x][y] > best)
          best = sum[x][y];
    
    return best;    
  }
  
  private boolean isPositionValid(int startRow, int startCol, int x, int y) {
    if (x > startRow && board[x][y] == board[x - 1][y])
      return false;
    if (y > startCol && board[x][y] == board[x][y - 1])
      return false;
    return true;
  }

}
