public class BrickWall554 {
  public int leastBricks(List<List<Integer>> wall) {
    int rows = wall.size();
    if (rows == 0) return 0;
    Set<Integer> [] rb = toRowsBorders(wall);
    Set<Integer> allBorders = new HashSet<>();
    Map<Integer, Integer> borderCounts = new HashMap<>();
    for (Set<Integer> row : rb) {
      for (int border : row) {
        borderCounts.put(border, borderCounts.getOrDefault(border, 0) + 1);
      }
    }
    int maxCount = 0;
    for(int count : borderCounts.values()) {
      if (count > maxCount) maxCount = count;
    }
    return rows - maxCount;
  }

  Set<Integer> [] toRowsBorders(List<List<Integer>> wall) {
    int n = wall.size();
    Set<Integer> [] res = new HashSet[n];
    int i = 0;
    for (List<Integer> row : wall) {
      res[i] = new HashSet<>();
      Iterator<Integer> it = row.iterator();
      int b = 0;
      while(it.hasNext()) {
        int brick = it.next();
        if (it.hasNext()) {
          b += brick;
          res[i].add(b);
        }
      }
      i++;
    }
    return res;
  }
}
