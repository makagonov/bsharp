package codeforces;

import java.io.*;
import java.util.*;

public class Fence363B {
  BufferedReader in;
  PrintWriter out;
  StringTokenizer st;

  public Fence363B() throws IOException{
    in = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(System.out);
    out.println(solve());
    out.close();
  }

  public String next() throws IOException {
    if (st == null || !st.hasMoreElements())
      st = new StringTokenizer(in.readLine());
    return st.nextToken();
  }

  public int nextInt() throws IOException { return Integer.parseInt(next());  }
  public long nextLong() throws IOException { return Long.parseLong(next()); }
  public double nextDouble() throws IOException { return Double.parseDouble(next()); }

  public int solve() throws IOException {
    int n = nextInt(), k = nextInt();
    int [] h = new int[n];
    for (int i = 0; i < n; i++)
      h[i] = nextInt();

    int curSum = 0;
    int bestIndex = 0;
    for (int i = 0; i < k; i++) {
      curSum += h[i];
    }

    int bestSum = curSum;

    for (int i = k; i < n; i++) {
      curSum += h[i];
      curSum -= h[i - k];
      if (curSum < bestSum) {
        bestSum = curSum;
        bestIndex = i - k + 1;
      }
    }
    return bestIndex + 1;
  }

  public static void main (String [] args) throws IOException {
    new Fence363B();
  }
}
