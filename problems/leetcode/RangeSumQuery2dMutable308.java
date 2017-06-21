public class RangeSumQuery2dMutable308 {
  class BIT {
    int [][] t;
    int n;
    int m;
    BIT(int [][] matrix) {
      this.n = matrix.length;
      this.m = matrix[0].length;
      this.t = new int[n + 1][m + 1];
      for (int i = 0; i < matrix.length; i++) {
        for (int j = 0; j < matrix[0].length; j++) {
          update(i + 1, j + 1, matrix[i][j]);
        }
      }
    }

    void set(int x, int y, int val) {
      int curVal = sum(x, y, x, y);
      int diff = val - curVal;
      update(x, y, diff);
    }

    void update(int x, int y, int val) {
      while(x <= n) {
        int y1 = y;
        while(y1 <= m) {
          t[x][y1] += val;
          y1 += (y1 & -y1);
        }
        x += (x & -x);

      }
    }

    public int sum(int a, int b) {
      int sum = 0;
      while(a > 0) {
        int b1 = b;
        while(b1 > 0) {
          sum += t[a][b1];
          b1 -= (b1 & -b1);
        }
        a -= (a & -a);
      }
      return sum;
    }

    public int sum(int a, int b, int c, int d) {
      int res = sum(c, d);
      if (a > 1) res -= sum(a - 1, d);
      if (b > 1) res -= sum(c, b - 1);
      if (a > 1 && b > 1) res += sum(a - 1, b - 1);
      return res;
    }
  }


  BIT btree;
  public RangeSumQuery2dMutable308(int[][] matrix) {
    int n = matrix.length;
    if (n > 0) this.btree = new BIT(matrix);
  }

  public void update(int row, int col, int val) {
    if (this.btree == null) return;
    this.btree.set(row + 1, col + 1, val);
  }

  public int sumRegion(int row1, int col1, int row2, int col2) {
    if (this.btree == null) return 0;
    return this.btree.sum(row1 + 1, col1 + 1, row2 + 1, col2 + 1);
  }
}

/**
 * Your NumMatrix object will be instantiated and called as such:
 * NumMatrix obj = new NumMatrix(matrix);
 * obj.update(row,col,val);
 * int param_2 = obj.sumRegion(row1,col1,row2,col2);
 */
