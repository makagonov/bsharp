import java.io.*;
import java.util.*;

public class HeapSort {
  BufferedReader in;
  PrintWriter out;
  StringTokenizer st;

  public HeapSort() throws IOException{
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

  public Integer nextInt() throws IOException { return Integer.parseInt(next());  }
  public long nextLong() throws IOException { return Long.parseLong(next()); }
  public double nextDouble() throws IOException { return Double.parseDouble(next()); }

  public void solve() throws IOException {
    int n = nextInt();
    MinHeap heap = new MinHeap(n);
    for (int i = 0; i < n; i++)
      heap.add(nextInt());

    while(!heap.isEmpty()) {
      out.print(heap.pop() + " ");
    }
    out.println();
  }

  public static void main (String [] args) throws IOException {
    new HeapSort();
  }
}
