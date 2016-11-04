package codeforces.template;

import java.io.*;
import java.util.*;

public class Solution {
  BufferedReader in;
  PrintWriter out;
  StringTokenizer st;

  public Solution() throws IOException{
    in = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(System.out);
    solve();
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

  public void solve() throws IOException {

  }

  public static void main (String [] args) throws IOException {
    new Solution();
  }
}
