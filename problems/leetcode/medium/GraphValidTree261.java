public class GraphValidTree261 {
  public boolean validTree(int n, int[][] edges) {
    int m = edges.length;
    if (m != n - 1) return false;
    boolean [] used = new boolean[n];
    List<Integer> [] g = toAdjecencyList(n, edges);
    dfs(0, used, g);
    for (int i = 0; i < n; i++) {
      if (!used[i]) return false;
    }
    return true;
  }

  void dfs(int v, boolean [] used, List<Integer> [] g) {
    used[v] = true;
    for (int i : g[v]) {
      if (!used[i]) {
        dfs(i, used, g);
      }
    }
  }

  List<Integer> [] toAdjecencyList(int n, int [][] edges) {
    List<Integer> [] g = new LinkedList[n];
    for (int i = 0; i < n; i++) {
      g[i] = new LinkedList<>();
    }
    for (int [] e : edges) {
      int from = e[0], to = e[1];
      g[from].add(to);
      g[to].add(from);
    }
    return g;
  }
}
