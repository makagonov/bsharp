public class ReverseStringII541 {
  public String reverseStr(String s, int k) {
    int n = s.length();
    if (k == 1 || n == 0) return s;
    StringBuilder sb = new StringBuilder();
    for (int i = 0, reverse = 1; i < n; i += k, reverse = 1 - reverse) {
      if (reverse == 1) {
        for (int j = Math.min(i + k - 1, n - 1); j >= i; j--) {
          sb.append(s.charAt(j));
        }
      } else {
        for (int j = i; j < Math.min(n, i + k); j++) {
          sb.append(s.charAt(j));
        }
      }
    }
    return sb.toString();
  }
}

