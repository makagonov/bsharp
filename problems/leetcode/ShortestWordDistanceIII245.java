public class ShortestWordDistanceIII245 {
  public int shortestWordDistance(String[] words, String word1, String word2) {
    Map<String, ArrayList<Integer>> indexes = new HashMap<>();
    for (int i = 0; i < words.length; i++) {
      ArrayList<Integer> wordIndexes = indexes.getOrDefault(words[i], new ArrayList<>());
      wordIndexes.add(i);
      indexes.put(words[i], wordIndexes);
    }

    if (word1.equals(word2)) return getSameWordResult(indexes.get(word1));
    return getDifferentWordResult(indexes.get(word1), indexes.get(word2));
  }

  int getDifferentWordResult(ArrayList<Integer> list1, ArrayList<Integer> list2) {
    int p1 = 0, p2 = 0;
    int best = Integer.MAX_VALUE;
    while(p1 < list1.size() && p2 < list2.size()) {
      boolean improved = false;
      int i1 = list1.get(p1);
      int i2 = list2.get(p2);
      int diff = Math.abs(i1 - i2);
      if (diff <= best) {
        best = diff;
        improved = true;
      }

      if (i1 < i2) {
        p1++;
      } else {
        p2++;
      }
    }
    return best;
  }

  int getSameWordResult(ArrayList<Integer> list) {
    int min = Integer.MAX_VALUE;
    for (int i = 1; i < list.size(); i++) {
      int diff = list.get(i) - list.get(i - 1);
      if (diff < min) min = diff;
    }
    return min;
  }
}
