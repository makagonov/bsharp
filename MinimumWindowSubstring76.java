public class MinimumWindowSubstring76 {
  boolean containsAll(Map<Character, Integer> cur, Map<Character, Integer> need) {
    for(Map.Entry<Character, Integer> entry : need.entrySet()) {
      if (cur.getOrDefault(entry.getKey(), 0) < entry.getValue()) {
        return false;
      }
    }
    return true;
  }

  Map<Character, Integer> getNeeded(String t) {
    Map<Character, Integer> res = new HashMap<>();
    for (int i = 0; i < t.length(); i++) {
      char c = t.charAt(i);
      res.put(c, res.getOrDefault(c, 0) + 1);
    }
    return res;
  }

  public String minWindow(String s, String t) {
    int n = s.length();
    if (n == 0) return "";

    Map<Character, Integer> need = getNeeded(t);
    int bestP1 = -1, bestP2 = -1, bestLen = -1;
    Map<Character, Integer> cur = new HashMap<>();
    for (int p1 = 0, p2 = 0; p2 < n; p2++) {
      char c = s.charAt(p2);
      if (!need.containsKey(c)) {
        continue;
      }
      cur.put(c, cur.getOrDefault(c, 0) + 1);
      if (containsAll(cur, need)) {
        while(p1 < p2) {
          c = s.charAt(p1);
          if (!need.containsKey(c)) {
            p1++;
          } else if (cur.getOrDefault(c, 0) > need.get(c)) {
            p1++;
            cur.put(c, cur.get(c) - 1);
          } else {
            break;
          }
        }
        int newLen = p2 - p1 + 1;
        if (bestLen < 0 || newLen < bestLen) {
          bestLen = newLen;
          bestP1 = p1;
          bestP2 = p2;
        }
      }
    }
    if (bestLen < 0) return "";
    return s.substring(bestP1, bestP2 + 1);
  }
}
