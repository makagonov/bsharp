public class ReverseWordsInStringIII557 {
  public String reverseWords(String s) {
    StringBuilder sb = new StringBuilder();
    StringBuilder cur = new StringBuilder();
    for (int i = 0; i < s.length(); i++) {
      char c = s.charAt(i);
      if (c == ' ') {
        sb.append(cur.reverse().toString());
        cur.setLength(0);
        sb.append(c);
      } else {
        cur.append(c);
      }
    }
    if (cur.length() > 0) sb.append(cur.reverse());
    return sb.toString();
  }
}
