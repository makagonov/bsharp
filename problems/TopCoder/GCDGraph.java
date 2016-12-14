import java.util.*;

public class GCDGraph {
  int [] p;
  Random rnd;
  public String possible(int n, int k, int x, int y) {
    if (k == 0 || x == y) return getResultFromBool(true);
    p = new int [n + 1];
    rnd = new Random();
    for (int i = 1; i <= n; i++)
      p[i] = i;
    
    for (int i = k + 1; i <= n; i++) {
      for (int j = i; j <= n; j += i) {
        union(find(i), find(j));
      }
    }
    return getResultFromBool(find(x) == find(y));
  }
  
  int find(int x) {
    if (p[x] == x) return x;
    return p[x] = find(p[x]);
  }
  
  void union(int x, int y) {
    int random = rnd.nextInt();
    if (random % 2 == 0) {
      p[x] = y;
    } else {
      p[y] = x;
    }
  }

  String getResultFromBool(boolean b) {
    return b ? "Possible" : "Impossible";
  }
}
