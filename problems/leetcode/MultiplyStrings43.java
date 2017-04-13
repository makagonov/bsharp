public class MultiplyStrings43 {
  int [] strToIntArrayRev(String s) {
    int [] ret = new int[s.length()];
    for (int i = 0; i < s.length(); i++) {
      ret[s.length() - 1 - i] = s.charAt(i) - '0';
    }
    return ret;
  }
  public String multiply(String num1, String num2) {
    int [] c = new int[10000];
    int [] a = strToIntArrayRev(num1);
    int [] b = strToIntArrayRev(num2);
    for (int i = 0; i < a.length; i++) {
      int carrier = 0;
      for (int j = 0; j < b.length; j++) {
        int total = c[i + j] + carrier + a[i] * b[j];
        c[i + j] = total % 10;
        carrier = total / 10;
      }
      for (int j = b.length; carrier > 0; j++) {
        c[i + j] = carrier % 10;
        carrier /= 10;
      }
    }

    StringBuilder sb = new StringBuilder();
    int start = c.length - 1;
    while(start > 0 && c[start] == 0) {
      start--;
    }
    for (int i = start; i >= 0; i--) {
      sb.append(Integer.toString(c[i]));
    }

    return sb.toString();
  }
}
