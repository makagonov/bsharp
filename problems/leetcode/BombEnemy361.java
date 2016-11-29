// Runtime complexity: O(N^2*LogN)
import java.util.*;

public class BombEnemy361 {
  public int maxKilledEnemies(char[][] g) {
    if (g.length == 0) return 0;
    //surrounding the grid with walls
    char [][] grid = new char[g.length + 2][g[0].length + 2];
    int n = grid.length, m = grid[0].length;
    for (int i = 0; i < n; i++)
      grid[i][0] = grid[i][m - 1] = 'W';

    for (int j = 0; j < m; j++)
      grid[0][j] = grid[n - 1][j] = 'W';

    for (int i = 1; i < n - 1; i++)
      for (int j = 1; j < m - 1; j++)
        grid[i][j] = g[i - 1][j - 1];
    
    List<Integer> [] wallsInRows = getWallsInRows(grid);
    List<Integer> [] wallsInColumns = getWallsInColumns(grid);

    int [][] rowSums = getRowsCumulativeSum(grid);
    int [][] colSums = getColumnsCumulativeSum(grid);

    int best = 0;
    for (int i = 1; i < n - 1; i++)
      for (int j = 1; j < m - 1; j++)
        if (grid[i][j] == '0') {
          int [] closestInRow = findClosestWallPositions(wallsInRows[i], j);
          int [] closestInCol = findClosestWallPositions(wallsInColumns[j], i);

          int sum = getPartialRowSum (rowSums, i, closestInRow[0], j)
              + getPartialRowSum (rowSums, i, j, closestInRow[1])
              + getPartialColSum (colSums , j, closestInCol[0], i)
              + getPartialColSum (colSums , j, i, closestInCol[1])
              ;
          if (sum > best)
            best = sum;

        }
    return best;

  }

  int getPartialRowSum(int [][] a, int row, int fromCol, int upToCol) {
    int res = a[row][upToCol];
    if (fromCol > 0) res -= a[row][fromCol - 1];
    return res;
  }

  int getPartialColSum(int [][] a, int col, int fromRow, int upToRow) {
    int res = a[upToRow][col];
    if (fromRow > 0) res -= a[fromRow - 1][col];
    return res;
  }

  int [] findClosestWallPositions(List<Integer> positions, int pos) {
    int lo = 0, hi = positions.size();
    while(lo < hi) {
      int mid = (lo + hi) / 2;
      if (positions.get(mid) < pos) {
        lo = mid + 1;
      } else {
        hi = mid;
      }
    }
    // returning two elements: first is the closest smaller than pos,
    // second - closest larger than pos
    return new int []{positions.get(lo - 1), positions.get(lo)};
  }

  List<Integer> [] getWallsInRows(char [][] grid) {
    List<Integer> [] result = new ArrayList[grid.length];
    for (int i = 0; i < grid.length; i++) {
      result[i] = new ArrayList<>();
      for (int j = 0; j < grid[0].length; j++) {
        if (grid[i][j] == 'W')
          result[i].add(j);
      }
    }
    return result;
  }

  List<Integer> [] getWallsInColumns(char [][] grid) {
    List<Integer> [] result = new ArrayList[grid[0].length];
    for (int j = 0; j < grid[0].length; j++) {
      result[j] = new ArrayList<>();
      for (int i = 0; i < grid.length; i++) {
        if (grid[i][j] == 'W')
          result[j].add(i);
      }
    }
    return result;
  }

  int [][] getColumnsCumulativeSum(char [][] grid) {
    int [][] a = new int [grid.length][grid[0].length];
    //skipping first and last column - they are all 'W'
    for (int col = 1; col < grid[0].length; col++)
      for (int i = 1; i < grid.length; i++)
        if (grid[i][col] == 'E')
          a[i][col] = a[i - 1][col] + 1;
        else
          a[i][col] = a[i - 1][col];
    return a;
  }

  int [][] getRowsCumulativeSum(char [][] grid) {
    int [][] a = new int [grid.length][grid[0].length];
    //skipping first and last row - they are all 'W'
    for (int row = 1; row < grid.length; row++)
      for (int j = 1; j < grid[0].length; j++)
        if (grid[row][j] == 'E')
          a[row][j] = a[row][j - 1] + 1;
        else
          a[row][j] = a[row][j - 1];
    return a;
  }
}