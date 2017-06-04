public class AddBinary67 {
  public String addBinary(String a, String b) {
    int carrier = 0;
    int n = a.length(), m = b.length();
    StringBuilder sb = new StringBuilder();
    for (int i = 1; i <= Math.max(n, m) || carrier > 0; i++) {
      int sum = carrier;
      if (n - i >= 0) sum += a.charAt(n - i) - '0';
      if (m - i >= 0) sum += b.charAt(m - i) - '0';
      carrier = sum / 2;
      sb.append(Integer.toString(sum % 2));
    }
    return sb.reverse().toString();
  }
}
