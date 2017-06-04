public class AddStrings415 {
  int getValAtPos(String s, int pos) {
    if (pos < 0 || pos >= s.length()) return 0;
    return s.charAt(pos) - '0';
  }
  public String addStrings(String num1, String num2) {
    int l1 = num1.length(), l2 = num2.length();
    int carrier = 0;
    StringBuilder sb = new StringBuilder();
    for (int i = 1; carrier > 0 || i <= Math.max(l1, l2); i++) {
      carrier += getValAtPos(num1, l1 - i);
      carrier += getValAtPos(num2, l2 - i);
      sb.append(Integer.toString(carrier % 10));
      carrier /= 10;
    }
    return sb.reverse().toString();
  }
}
