import java.util.*;

public class GeneralizedAbbreviation320 {
  List<String> rec(List<String> [] memo, String s, int pos) {
    if (memo[pos] != null) return memo[pos];
    int n = s.length();
    List<String> result = new LinkedList<>();
    if (pos >= n) {
      result.add("");
      return memo[pos] = result;
    }

    for (int replace = 0; replace <= n - pos; replace++) {
      List<String> suffRes = rec(memo, s, pos + replace + 1);
      String prefix = replace == 0 ? "" + s.charAt(pos) : Integer.toString(replace);
      if (replace > 0 && pos + replace < n) {
        prefix += s.charAt(pos + replace);
      }
      for (String suff : suffRes) {
        result.add(prefix + suff);
      }
    }
    memo[pos] = result;
    return result;
  }

  public List<String> generateAbbreviations(String word) {
    return rec(new LinkedList[word.length() + 2], word, 0);
  }
}
