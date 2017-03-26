import java.util.*;

public class RestoreIPAddresses93 {
  void rec(List<String> acc, StringBuilder cur, String s, int pos, int left) {
    if (left == 0) {
      if (pos == s.length()) {
        acc.add(cur.toString());
      }
      return;
    }
    if (pos == s.length()) {
      return;
    }
    int prevLength = cur.length();
    String prefix = "";
    for (int i = pos; i < Math.min(s.length(), pos + 3); i++) {
      char c = s.charAt(i);
      prefix += c;
      int prefixVal = Integer.parseInt(prefix);
      if (prefixVal > 255 || Integer.toString(prefixVal).length() < prefix.length()) break;
      cur.append(prefix);
      if (left > 1) cur.append('.');
      rec(acc, cur, s, i + 1, left - 1);
      cur.setLength(prevLength);
    }
  }
  public List<String> restoreIpAddresses(String s) {
    List<String> result = new LinkedList<>();
    rec(result, new StringBuilder(), s, 0, 4);
    return result;
  }
}
