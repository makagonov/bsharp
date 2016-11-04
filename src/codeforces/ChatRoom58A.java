package codeforces;

import java.io.*;
import java.util.*;

public class ChatRoom58A {
  BufferedReader in;
  PrintWriter out;
  StringTokenizer st;

  public ChatRoom58A() throws IOException{
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
    String word = "hello";
    String line = in.readLine();
    int pA = 0, pB = 0;
    while(pA < word.length() && pB < line.length()) {
      if (word.charAt(pA) == line.charAt(pB)) {
        pA++;
        pB++;
      } else {
        pB++;
      }
    }
    if (pA == word.length()) {
      out.println("YES");
    } else {
      out.println("NO");
    }
  }

  public static void main (String [] args) throws IOException {
    new ChatRoom58A();
  }
}
