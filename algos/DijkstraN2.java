import java.io.*;
import java.util.*;

public class DijkstraN2 {
  private final int [][] weights;
  private final int n;
  private static final int oo = Integer.MAX_VALUE / 2;
  
  public DijkstraN2(int n, int [][] weights) {
    this.n = n;
    
    this.weights = new int[n][];
    for (int i = 0; i < n; i++)
      this.weights[i] = weights[i].clone();
  }
  
  public LinkedList<Integer> findShortestPath(int source, int target) {
    LinkedList<Integer> path = new LinkedList<>();
    int [] dist = new int[n];
    Arrays.fill(dist, oo);
    dist[source] = 0;
    
    int [] parent = new int[n];
    Arrays.fill(parent, -1);
    
    boolean [] used = new boolean[n];
    
    for (int i = 0; i < n; i++) {
      int vertex = -1;
      for (int j = 0; j < n; j++) {
        if (used[j]) continue;
        if (vertex < 0 || dist[vertex] > dist[j])
          vertex = j;
      }
      
      used[vertex] = true;
        
      for (int j = 0; j < n; j++) {
        int candidateDist = dist[vertex] + weights[vertex][j]; 
        if (dist[j] > candidateDist) {
          dist[j] = candidateDist;
          parent[j] = vertex;
        }
      }
    }
    
    if (dist[target] == oo) {
      return null;
    }
    
    int v = target;
    while(v != -1) {
      path.addFirst(v);
      v = parent[v];
    }
    
    return path;
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
  }
  
  public static void main (String [] args) throws Exception {
    FastReader in = new FastReader();
    PrintWriter out = new PrintWriter(System.out);
    
    int n = in.nextInt(), s = in.nextInt() - 1, t = in.nextInt() - 1;
    int [][] weights = new int[n][n];
    for (int i = 0; i < n; i++)
      for (int j = 0; j < n; j++) {
        int w = in.nextInt();
        weights[i][j] = w < 0 ? oo : w;
      }
        
    
    DijkstraN2 dijkstra = new DijkstraN2(n, weights);
    LinkedList<Integer> path = dijkstra.findShortestPath(s, t);
    if (path == null) {
      out.println(-1);
    } else {
      for (int i : path)
        out.print((i + 1) + " ");
      out.println();
    }
    out.close();
  }

}
