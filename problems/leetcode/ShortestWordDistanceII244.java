public class ShortestWordDistanceII244 {
  Map<String, List<Integer>> indexes;
  public WordDistance(String[] words) {
    indexes = new HashMap<>();
    for (int i = 0; i < words.length; i++) {
      List<Integer> wordIndexes = indexes.getOrDefault(words[i], new ArrayList<>());
      wordIndexes.add(i);
      indexes.put(words[i], wordIndexes);
    }
  }

  public int shortest(String word1, String word2) {
    List<Integer> indexes1 = indexes.get(word1);
    List<Integer> indexes2 = indexes.get(word2);
    int p1 = 0, p2 = 0, best = Integer.MAX_VALUE;
    boolean improved = false;
    while(p1 < indexes1.size() && p2 < indexes2.size()) {
      improved = false;
      int i1 = indexes1.get(p1);
      int i2 = indexes2.get(p2);
      int diff = Math.abs(i1 - i2);
      if (diff < best) {
        best = diff;
        improved = true;
      }

      if ((i1 == indexes1.size() - 1 || i2 == indexes2.size() - 1) && !improved) {
        break;
      }

      if (i1 < i2)
        p1++;
      else p2++;
    }
    return best;
  }
}

