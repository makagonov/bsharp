import java.util.*;

public class BuildingRoutes {
  int [][] minDist;
  int [][] edges;
  boolean [][][][] usedEdges;
  int n;
  public int buildMineTLELastTest(String[] dist, int T) {
    n = dist.length;
    minDist = new int[n][n];
    usedEdges = new boolean[n][n][n][n];
    edges = new int [n][n];

    for (int i = 0; i < n; i++)
      usedEdges[i][i][i][i] = true;

    for (int i = 0; i < n; i++)
      for (int j = 0; j < n; j++)
        edges[i][j] = minDist[i][j] = (int)(dist[i].charAt(j) - '0');
    
    // first, finding shortest distance between each pair of cities
    for (int k = 0; k < n; k++)
      for (int i = 0; i < n; i++)
        for(int j = 0; j < n; j++)
          if (minDist[i][k] + minDist[k][j] < minDist[i][j])
            minDist[i][j] = minDist[i][k] + minDist[k][j];

    // generating all possible routes from city "from" to city "to", 
    // s.t. total distance was equal to the distance found by before
    for (int from = 0; from < n; from++) {
      for (int to = 0; to < n; to++) {
        if (from == to) continue;
        rec(from, to, new LinkedList<>(Arrays.asList(from)), 0);
      }
    }

    int totalSum = 0;
    for (int i = 0; i < n; i++)
      for (int j = 0; j < n; j++) {
        int cnt = 0;
        for (int k = 0; k < n; k++)
          for (int f = 0; f < n; f++)
            if (usedEdges[i][j][k][f])
              cnt++;
              
        if (cnt >= T)
          totalSum += edges[i][j];
      }

    return totalSum;
  }

  public void rec(int from, int to, LinkedList<Integer> cities, int currentSum) {
    if (currentSum > minDist[from][to])
      return;
    int last = cities.peekLast();
    if (last == to && currentSum == minDist[from][to]) {
      // this is one of the shortest paths from "from" to "to"
      // increasing counters for all the edges on the way
      int prevV = -1;
      for (int v : cities) {
        if (prevV >= 0)
          usedEdges[prevV][v][from][to] = true;
        prevV = v;
      }
      return;
    }
    for (int i = 0; i < n; i++) {
      if (i == last) continue;
      cities.addLast(i);
      rec(from, to, cities, currentSum + edges[last][i]);
      cities.pollLast();
    }
  }

  public int buildPetrPassesSysTest(String[] dist, int T) {
    int n = dist.length;
    int[][] e = new int[n][n];
    int[][] d = new int[n][n];
    for (int i = 0; i < n; ++i)
      for (int j = 0; j < n; ++j) {
        e[i][j] = d[i][j] = dist[i].charAt(j) - '0';
      }
    for (int k = 0; k < n; ++k)
      for (int i = 0; i < n; ++i)
        for (int j = 0; j < n; ++j)
          d[i][j] = Math.min(d[i][j], d[i][k] + d[k][j]);
    int res = 0;
    for (int i = 0; i < n; ++i)
      for (int j = 0; j < n; ++j) if (i != j) {
        int cnt = 0;
        for (int a = 0; a < n; ++a)
          for (int b = 0; b < n; ++b) if (a != b) {
            if (d[a][i] + e[i][j] + d[j][b] == d[a][b]) ++cnt;
          }
        if (cnt >= T) {
          res += e[i][j];
        }
      }
    return res;
  }



}
