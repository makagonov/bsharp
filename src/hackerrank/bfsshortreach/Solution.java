package hackerrank.bfsshortreach;

import java.util.*;

public class Solution {

  private static final int LARGE_DIST = Integer.MAX_VALUE / 2;

  Scanner in;
  public Solution () {
    in = new Scanner(System.in);
  }

  public void solve() {
    int q = in.nextInt();
    while(q > 0) {
      solveNext();
      q--;
    }
  }

  private void solveNext() {
    int n = in.nextInt();
    int m = in.nextInt();

    List<Integer>[] edges = new ArrayList[n];
    for (int i = 0; i < n; i++) {
      edges[i] = new ArrayList<Integer>();
    }
    for (int i = 0; i < m; i++) {
      int a = in.nextInt() - 1, b = in.nextInt() - 1;
      edges[a].add(b);
      edges[b].add(a);
    }

    int s = in.nextInt() - 1;
    int [] distances = new int[n];
    Arrays.fill(distances, LARGE_DIST);
    distances[s] = 0;

    Queue<Node> q = new LinkedList<Node>();
    q.add(new Node(s, 0));
    while (!q.isEmpty()) {
      Node head = q.poll();
      int nextDistance = head.distance + 6;
      for (int v : edges[head.vertex]) {
        if (distances[v] > nextDistance) {
          distances[v] = nextDistance;
          q.add(new Node(v, nextDistance));
        }
      }
    }

    for (int i = 0; i < n; i++) {
      if (i == s) continue;
      if (distances[i] == LARGE_DIST) System.out.print("-1 ");
      else System.out.print(distances[i] + " ");
    }
    System.out.println();
  }

  static class Node {
    int vertex;
    int distance;
    public Node(int v, int d) {
      this.vertex = v;
      this.distance = d;
    }
  }

  public static void main (String [] args) {
    new Solution().solve();
  }
}
