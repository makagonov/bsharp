import java.util.*;
import java.io.*;

public class PieProgress {
  static final long oo = Integer.MAX_VALUE;
  
  public static void main(String[] args) throws Exception {
    new PieProgress();
  }
  
  public PieProgress() throws Exception {
    FastReader reader = new FastReader();
    int T = reader.nextInt();
    for (int t = 1; t <= T; t++) {
      int n = reader.nextInt();
      int m = reader.nextInt();
      int [][] c = new int[n][m];
      for (int i = 0; i < n; i++)
        for (int j = 0; j < m; j++)
             c[i][j] = reader.nextInt();
      System.out.printf("Case #%d: %d\n", t, solve(n, m, c));
    }
  }
  
  long solve(int n, int m, int [][] c) {
    long [][] dp = new long[n + 1][n + 1];
    for (int i = 0; i <= n; i++)
      Arrays.fill(dp[i], oo);
    
    for (int i = 0; i < n; i++)
      Arrays.sort(c[i]);
    
    long [][] sums = new long[n + 1][m + 1];
    for (int i = 1; i <= n; i++) {
      long sum = 0L;
      for (int j = 1; j <= m; j++) {
        sum += c[i - 1][j - 1];
        sums[i][j] = sum + j * j;
      }
    }
    
    for (int i = 1; i <= Math.min(n, m); i++) {
      dp[1][i] = sums[1][i];
    }
    
    for (int i = 2; i <= n; i++) {
      for (int j = i; j <= n; j++) {
        for (int k = 0; j - k >= 0 && k <= m; k++) {
          dp[i][j] = Math.min(dp[i][j], dp[i - 1][j - k] + sums[i][k]);
        }
      }
    }
    
    return dp[n][n];
  }

  static class FastReader {
    private BufferedReader in;
    private StringTokenizer st;
    FastReader() {
      in = new BufferedReader(new InputStreamReader(System.in));
    }

    FastReader(String fileName) throws Exception {
      in = new BufferedReader(new FileReader(fileName));
    }

    String next() throws Exception {
      if (st == null || !st.hasMoreTokens())
        st = new StringTokenizer(in.readLine());
      return st.nextToken();
    }

    int nextInt() throws Exception { return Integer.parseInt(next()); }
    long nextLong() throws Exception { return Long.parseLong(next()); }
    double nextDouble() throws Exception { return Double.parseDouble(next()); }
    int [] readIntArray(int n) throws Exception {
      int [] a = new int [n];
      for (int i = 0; i < n; i++)
        a[i] = nextInt();
      return a;
    }

  }
}
