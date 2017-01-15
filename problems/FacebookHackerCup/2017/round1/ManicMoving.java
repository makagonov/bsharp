import java.util.*;
import java.io.*;

public class ManicMoving {
  static final int oo = Integer.MAX_VALUE / 6;
  
  public static void main(String[] args) throws Exception {
    new ManicMoving();
  }
  
  FastReader reader;
  
  public ManicMoving() throws Exception {
    reader = new FastReader();
    int T = reader.nextInt();
    for (int t = 1; t <= T; t++) {
      System.out.printf("Case #%d: %d\n", t, solveTestCase());
    }
  }

  int solveTestCase() throws Exception {
    int n = reader.nextInt();
    int m = reader.nextInt();
    int K = reader.nextInt();
    
    int[][] dist = new int[n][n];
    for (int i = 0; i < n; i++) {
      Arrays.fill(dist[i], oo);
      dist[i][i] = 0;
    }
    
    for (int i = 0; i < m; i++) {
      int from = reader.nextInt() - 1,
          to = reader.nextInt() - 1,
          cost = reader.nextInt();
      dist[from][to] = dist[to][from] = Math.min(dist[from][to], cost);
    }

    int[] s = new int[K + 1];
    int[] d = new int[K + 1];
    for (int i = 1; i <= K; i++) {
      s[i] = reader.nextInt() - 1;
      d[i] = reader.nextInt() - 1;
    }

    for (int k = 0; k < n; k++)
      for (int i = 0; i < n; i++)
        for (int j = 0; j < n; j++)
          dist[i][j] = Math.min(dist[i][j], getDistance(dist, i, k, j));
    
    int[] dp = new int[K + 1];
    Arrays.fill(dp, oo);
    dp[0] = 0;
    for (int i = 1; i <= K; i++) {
      dp[i] = Math.min(dp[i], dp[i - 1] + getDistance(dist, d[i - 1], s[i], d[i]));
      if (i > 1) {
        dp[i] = Math.min(dp[i], dp[i - 2] + getDistance(dist, d[i - 2], s[i - 1], s[i], d[i - 1], d[i]));
      }
    }
    return dp[K] == oo ? -1 : dp[K];
  }

  int getDistance(int[][] dist, int... cities) {
    int sum = 0;
    for (int i = 1; i < cities.length; i++)
      sum += dist[cities[i - 1]][cities[i]];
    
    return sum;
  }

  class FastReader {
    BufferedReader in;
    StringTokenizer st;
    
    FastReader() {
      in = new BufferedReader(new InputStreamReader(System.in));
    }
    
    String next() throws Exception {
      if (st == null || !st.hasMoreTokens())
        st = new StringTokenizer(in.readLine());
      return st.nextToken();
    }

    int nextInt() throws Exception { return Integer.parseInt(next()); }
    long nextLong() throws Exception { return Long.parseLong(next()); }
    double nextDouble() throws Exception { return Double.parseDouble(next()); }
  }
}
