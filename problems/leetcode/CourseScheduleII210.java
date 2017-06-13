public class CourseScheduleII210 {
  private static final int USED_COLOR = 2;

  public int[] findOrder(int n, int[][] prerequisites) {
    Set<Integer> [] edges = new HashSet[n];
    for (int i = 0; i < n; i++) {
      edges[i] = new HashSet<>();
    }
    for (int [] i : prerequisites) {
      int a = i[0], b = i[1];
      edges[a].add(b);
    }

    int [] colors = new int[n];
    List<Integer> res = new LinkedList<>();
    for (int i = 0; i < n; i++) {
      if (colors[i] != USED_COLOR) {
        boolean ok = dfs(colors, edges, res, i);
        if (!ok) return new int[0];
      }
    }
    return listToArray(res);
  }

  int [] listToArray(List<Integer> list) {
    int [] ret = new int[list.size()];
    int i = 0;
    for (int j : list) {
      ret[i++] = j;
    }
    return ret;
  }

  boolean dfs(int [] colors, Set<Integer> [] edges, List<Integer> res, int v) {
    colors[v] = 1;
    for(int i : edges[v]) {
      if (colors[i] == USED_COLOR) {
        continue;
      }
      if (colors[i] == colors[v]) {
        return false;
      }
      boolean ok = dfs(colors, edges, res, i);
      if (!ok) return false;
    }
    colors[v] = USED_COLOR;
    res.add(v);
    return true;
  }

  // void rec
}
