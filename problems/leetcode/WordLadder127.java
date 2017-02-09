import java.util.*;

public class WordLadder127 {
  public int ladderLength(String beginWord, String endWord, List<String> wordList) {
    int n = wordList.size();
    int targetIndex = -1;
    for (int i = 0; i < n; i++)
      if (wordList.get(i).equals(endWord)) {
        targetIndex = i + 1;
        break;
      }
    if (targetIndex < 0) return 0;
    
    List<Integer>[] g = new LinkedList[n + 1];
    for (int i = 0; i < g.length; i++)
      g[i] = new LinkedList<>();

    for (int i = 0; i < n; i++) {
      boolean ok = areWordsAdjacent(beginWord, wordList.get(i));
      if (ok) {
        g[0].add(i + 1);
      }
    }

    for (int i = 0; i < n; i++) {
      String w1 = wordList.get(i);
      for (int j = i + 1; j < n; j++) {
        String w2 = wordList.get(j);
        boolean ok = areWordsAdjacent(w1, w2);
        if (ok) {
          g[i + 1].add(j + 1);
          g[j + 1].add(i + 1);
        }
      }
    }

    boolean[] used = new boolean[n + 1];
    Set<Integer>[] s = new HashSet[2];
    for (int i = 0; i < 2; i++)
      s[i] = new HashSet<>();
    s[0].add(0);
    used[0] = true;
    for (int i = 0, cur = 0; s[cur].size() > 0; i++, cur = 1 - cur) {
      s[1 - cur].clear();
      for (int j : s[cur])
        if (j == targetIndex)
          return i + 1;


      for (int j : s[cur]) {
        for (int k : g[j]) {
          if (!used[k]) {
            s[1 - cur].add(k);
            used[k] = true;
          }
        }
      }
    }
    return 0;
  }

  boolean areWordsAdjacent(String w1, String w2) {
    if (w1.length() != w2.length()) return false;
    int diffCount = 0;
    for (int i = 0; i < w1.length(); i++)
      if (w1.charAt(i) != w2.charAt(i)) diffCount++;
    return diffCount == 1;
  }
}
