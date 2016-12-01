import java.util.*;

public class Egalitarianism {
  
  public int maxDifference(String[] isFriend, int d) {
    int n = isFriend.length;
    boolean [][] g = new boolean[n][n];
    for (int i = 0; i < n; i++)
      for (int j = 0; j < n; j++)
        g[i][j] = isFriend[i].charAt(j) == 'Y';
    
    int best = 0;
    for (int i = 0; i < n; i++) {
      //hanging the graph, holding it at vertex i
      //and starting the BFS
      Queue<Integer> q = new LinkedList<>();
      boolean [] used = new boolean[n];
      int [] balance = new int [n];
      q.add(i);
      used[i] = true;
      
      while(!q.isEmpty()) {
        int v = q.poll();
        for (int j = 0; j < n; j++) {
          if (g[v][j] && !used[j]) {
            q.add(j);
            balance[j] = balance[v] + d;
            used[j] = true;
          }
        }
      }
      
      int max = 0;
      for (int j = 0; j < n; j++) {
        if (!used[j]) { //more than 1 connected component, no finite result
          return -1;
        }
        if (balance[j] > max)
          max = balance[j];
      }
      if (max > best)
        best = max;
    }
    
    return best;
  }

}
