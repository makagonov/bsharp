public class NestedListWeightSumII364 {
  void rec(List<Integer> levelSums, NestedInteger ni, int level) {
    if (ni.isInteger()) {
      while(levelSums.size() <= level) {
        levelSums.add(0);
      }
      levelSums.set(level, levelSums.get(level) + ni.getInteger());
      return;
    }
    for (NestedInteger item : ni.getList()) {
      rec(levelSums, item, level + 1);
    }
  }

  public int depthSumInverse(List<NestedInteger> nestedList) {
    List<Integer> levelSums = new ArrayList<>();
    for (NestedInteger ni : nestedList) {
      rec(levelSums, ni, 0);
    }

    int result = 0;
    for (int i = 0, curLevel = levelSums.size(); curLevel > 0; i++, curLevel--) {
      result += levelSums.get(i) * curLevel;
    }
    return result;
  }
}
