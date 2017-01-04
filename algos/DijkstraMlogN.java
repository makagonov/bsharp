import java.io.*;
import java.util.*;

public class DijkstraMlogN {
  private final int n;
  private static final int oo = 2009000999;
  private List<Map.Entry<Integer, Integer>> [] edges;
  
  public DijkstraMlogN(int n, List<Map.Entry<Integer, Integer>> [] edges) {
    this.n = n;
    this.edges = edges;
  }
  
  public int [] findShortestPaths(int source) {
    int [] dist = new int[n];
    Arrays.fill(dist, oo);
    dist[source] = 0;
    
    Queue<Node> q = new PriorityQueue<>();
    q.add(new Node(source, 0));
    
    while(!q.isEmpty()) {
      Node node = q.poll();
      if (dist[node.vertex] < node.distance) {
        // the vertex was already processed with better distance found
        continue;
      }
      for (Map.Entry<Integer, Integer> edge : edges[node.vertex]) {
        int candidateV = edge.getKey();
        int candidateDist = edge.getValue() + node.distance;
        if (candidateDist < dist[candidateV]) {
          dist[candidateV] = candidateDist; 
          q.add(new Node(candidateV, candidateDist));
        }
      }
    }
    
    return dist;
  }
  
  class Node implements Comparable<Node> {
    int vertex;
    int distance;
    public Node(int v, int d) {
      vertex = v;
      distance = d;
    }
    
    @Override
    public int compareTo(Node o) {
      return distance - o.distance;
    }
    
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
    int tests = in.nextInt();
    for (int test = 0; test < tests; test++) {
      int n = in.nextInt(), m = in.nextInt();
      List<Map.Entry<Integer, Integer>>[] edges = new LinkedList[n];
      for (int i = 0; i < n; i++)
        edges[i] = new LinkedList<>();
      
      for (int i = 0; i < m; i++) {
        int from = in.nextInt(), to = in.nextInt(), w = in.nextInt();
        edges[from].add(new AbstractMap.SimpleEntry<Integer, Integer>(to, w));
        edges[to].add(new AbstractMap.SimpleEntry<Integer, Integer>(from, w));
      }
      
      int source = in.nextInt();
      
      DijkstraMlogN dijkstra = new DijkstraMlogN(n, edges);
      int [] dist = dijkstra.findShortestPaths(source);
      for (int i : dist) out.print(i + " ");
      out.println();
    }
    
    out.close();
  }

}
