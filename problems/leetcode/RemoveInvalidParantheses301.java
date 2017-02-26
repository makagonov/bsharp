public class Solution {
  public List<String> removeInvalidParentheses(String s) {
    int n = s.length();
    HashSet<String> [] sets = new HashSet[2];
    for (int i = 0; i < 2; i++)
      sets[i] = new HashSet<>();
    sets[0].add(s);
    int cur = 0;
    List<String> result = new ArrayList();
    for (int steps = 0;; steps++, cur = 1 - cur) {
      for (String t : sets[cur]) {
        if (isValid(t)) result.add(t);
      }
      if (result.size() > 0)
        break;
      for (String t : sets[cur]) {
        for (int i = 0; i < t.length(); i++) {
          char c = t.charAt(i);
          if (c == '(' || c == ')') {
            String without = i > 0 ? t.substring(0, i) : "";
            if (i + 1 < t.length()) without += t.substring(i + 1);
            sets[1 - cur].add(without);
          }
        }
      }
    }
    return result;
  }
  boolean isValid(String s) {
    int count = 0;
    for (int i = 0; i < s.length(); i++) {
      if (s.charAt(i) == '(') count++;
      if (s.charAt(i) == ')') {
        count--;
        if (count < 0) return false;
      }
    }
    return count == 0;
  }
}
