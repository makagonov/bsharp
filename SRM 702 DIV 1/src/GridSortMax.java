import java.util.*;

public class GridSortMax {

  public String findMax(int n, int m, int[] grid) {
    if (n == 1 || m == 1) {
      return getAllOnes(Math.max(n, m));
    }

    int[][] grid2d = new int[n][m];
    int[][] sortedGrid2d = new int[n][m];
    for (int i = 0, k = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        grid2d[i][j] = grid[k++];
        sortedGrid2d[i][j] = k;
      }
    }

    boolean[] usedRow = new boolean[n];
    boolean[] fixedCols = new boolean[m];
    String finalResult = "";

    for (int row = 0; row < n; row++) {
      RowResultHolder best = null;
      for (int candidateRow = 0; candidateRow < n; candidateRow++) {
        if (usedRow[candidateRow])
          continue;
        RowResultHolder rowResult = new RowResultHolder(candidateRow, grid2d[candidateRow], sortedGrid2d[row], fixedCols);
        for (int j = 0; j < m; j++) {
          if (rowResult.fixedCols[j])
            continue;

          int mustBe = sortedGrid2d[row][j];
          for (int k = 0; k < m; k++) {
            if (rowResult.row[k] == mustBe && !rowResult.fixedCols[k]) {
              rowResult.fixedCols[j] = true;
              if (k != j) {
                swap(rowResult.row, k, j);
                rowResult.swaps.add(new int[] { k, j });
              }
            }
          }
        }
        if (best == null || best.compareTo(rowResult) < 0) {
          best = rowResult;
        }
      }
      finalResult += best.getSimilarityString();
      if (best.similarities == 0) {
        // not fixing the row if similarities is 0
        // so that future rows had more rows to choose from
        continue;
      }

      usedRow[best.rowNumber] = true;
      fixedCols = best.fixedCols;
      for (int[] swapPositions : best.swaps) {
        swapColumns(grid2d, swapPositions[0], swapPositions[1]);
      }

    }
    return finalResult;
  }

  private class RowResultHolder implements Comparable<RowResultHolder> {
    int rowNumber;
    int similarities;
    int[] row;
    int[] sortedRow;
    boolean[] fixedCols;
    List<int[]> swaps;
    private String similarityString;

    public RowResultHolder(int rowNumber, int[] row, int[] sortedRow, boolean[] fixedCols) {
      this.rowNumber = rowNumber;
      this.row = row.clone();
      this.sortedRow = sortedRow;
      this.fixedCols = fixedCols.clone();
      this.swaps = new ArrayList<>();
    }

    public String getSimilarityString() {
      if (similarityString != null)
        return similarityString;
      
      char[] ret = new char[row.length];
      Arrays.fill(ret, '0');
      for (int i = 0; i < row.length; i++)
        if (row[i] == sortedRow[i]) {
          ret[i] = '1';
          similarities++;
        }
      similarityString = new String(ret);
      return similarityString;
    }

    @Override
    public int compareTo(RowResultHolder o) {
      return this.getSimilarityString().compareTo(o.getSimilarityString());
    }
  }

  private String getAllOnes(int n) {
    char[] a = new char[n];
    Arrays.fill(a, '1');
    return new String(a);
  }

  private void swap(int[] a, int pos1, int pos2) {
    int tmp = a[pos1];
    a[pos1] = a[pos2];
    a[pos2] = tmp;
  }

  private void swapColumns(int[][] a, int col1, int col2) {
    for (int i = 0; i < a.length; i++) {
      swap(a[i], col1, col2);
    }
  }

}
