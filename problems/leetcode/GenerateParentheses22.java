public class GenerateParentheses22 {
  char [] brackets = new char[]{'(', ')'};
  void rec(List<String> acc, StringBuilder sb, int numOpen, int numClosed, int n) {
    if (numClosed > numOpen || numOpen - numClosed > n - sb.length()) return;
    if (sb.length() == n) {
      if (numOpen == numClosed) {
        acc.add(sb.toString());
      }
      return;
    }
    for (char c : brackets) {
      sb.append(c);
      rec(acc, sb, c == '(' ? numOpen + 1 : numOpen, c == ')' ? numClosed + 1 : numClosed, n);
      sb.setLength(sb.length() - 1);
    }
  }
  public List<String> generateParenthesis(int n) {
    List<String> result = new LinkedList<>();
    rec(result, new StringBuilder(), 0, 0, n * 2);
    return result;
  }
}
