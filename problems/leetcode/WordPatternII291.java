import java.util.*;

public class WordPatternII291 {
  boolean can(Map<Character, Integer> map, HashSet<String> used, String pattern, int pIndex, String str, int strIndex) {
    int n = pattern.length(), m = str.length();
    if (pIndex == n && strIndex == m) return true;
    if (pIndex == n || strIndex == m) return false;
    char next = pattern.charAt(pIndex);
    if (map.containsKey(next)) {
      int val = map.get(next);
      int from = val / 2000;
      int to = val % 2000;
      int charPatLen = to - from;
      if (charPatLen + strIndex > m) return false;
      for (int i = from; i < to; i++) {
        if (str.charAt(i) != str.charAt(i - from + strIndex)) {
          return false;
        }
      }
      return can(map, used, pattern, pIndex + 1, str, strIndex + charPatLen);
    }

    for (int i = strIndex + 1; i <= m; i++) {
      String charPat = str.substring(strIndex, i);
      if (used.contains(charPat)) continue;
      map.put(next, strIndex * 2000 + i);
      used.add(charPat);
      boolean good = can(map, used, pattern, pIndex + 1, str, i);
      map.remove(next);
      used.remove(charPat);
      if (good) return true;
    }
    return false;
  }

  public boolean wordPatternMatch(String pattern, String str) {
    return can(new HashMap<>(), new HashSet<>(), pattern, 0, str, 0);
  }
}
