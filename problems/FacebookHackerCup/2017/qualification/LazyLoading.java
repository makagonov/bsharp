import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


public class LazyLoading {
  BufferedReader in;
  StringTokenizer st;
  final int MIN_WEIGHT_PER_TRIP = 50;

  String next() throws Exception {
    if (st == null || !st.hasMoreTokens()) {
      st = new StringTokenizer(in.readLine());
    }
    return st.nextToken();
  }
  int nextInt() throws Exception {
    return Integer.parseInt(next());
  }
  public LazyLoading() throws Exception {
    in = new BufferedReader(new InputStreamReader(System.in));
    int T = nextInt();
    for (int i = 1; i <= T; i++) {
      int n = nextInt();
      int [] weights = new int[n];
      for (int j = 0; j < n; j++)
        weights[j] = nextInt();
      System.out.println("Case #" + i + ": " + solve(n, weights));
    }
  }

  private int solve(int n, int [] weights) {
    Arrays.sort(weights);
    boolean [] used = new boolean[n];
    int tripsCount = 0;
    for (int i = n - 1; i >= 0; i--) {
      if (used[i]) break;
      used[i] = true;
      int tripWeight = weights[i];
      int weightIncrement = weights[i];
      for (int j = 0; tripWeight < MIN_WEIGHT_PER_TRIP && j < n; j++) {
        if (used[j]) continue;
        used[j] = true;
        tripWeight += weightIncrement;
      }

      if (tripWeight >= MIN_WEIGHT_PER_TRIP) {
        tripsCount++;
      }
    }
    return Math.max(tripsCount, 1);
  }

  public static void main (String [] args) throws Exception {
    new LazyLoading();
  }
}
