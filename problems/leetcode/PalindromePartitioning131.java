public class PalindromePartitioning131 {
  public List<List<String>> partition(String s) {
    List<List<String>> res = new LinkedList<>();
    if (s.length() == 0) return res;
    rec(s, 0, new LinkedList<>(), res);
    return res;
  }

  void rec(String s, int pos, LinkedList<String> curr, List<List<String>> acc) {
    int n = s.length();
    if (pos == n) {
      acc.add(new LinkedList<>(curr));
      return;
    }
    for (int i = pos; i < n; i++) {
      boolean isPalin = true;
      for (int j = pos, k = i; j < k; j++, k--) {
        if (s.charAt(j) != s.charAt(k)) {
          isPalin = false;
          break;
        }
      }
      if (!isPalin) continue;
      curr.add(s.substring(pos, i + 1));
      rec(s, i + 1, curr, acc);
      curr.pollLast();
    }
  }

}
