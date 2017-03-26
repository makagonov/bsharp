import java.util.*;

public class WordBreakII140 {
  List<String> rec(Map<Integer, List<String>> memo, Set<String> dict, String s, int pos) {
    if (memo.containsKey(pos)) {
      return memo.get(pos);
    }
    List<String> res = new LinkedList<>();
    if (pos == s.length()) {
      res.add("");
    }
    for (String word : dict) {
      if (word.length() + pos > s.length()) continue;
      boolean equal = true;
      for (int i = 0; i < word.length(); i++) {
        if (word.charAt(i) != s.charAt(pos + i)) {
          equal = false;
          break;
        }
      }
      if (!equal) continue;
      List<String> suffixRes = rec(memo, dict, s, pos + word.length());
      for (String right : suffixRes) {
        if (right.length() > 0) res.add(word + " " + right);
        else res.add(word);
      }
    }
    memo.put(pos, res);
    return res;
  }

  public List<String> wordBreak(String s, List<String> wordDict) {
    return rec(new HashMap<>(), new HashSet<>(wordDict), s, 0);
  }
}
