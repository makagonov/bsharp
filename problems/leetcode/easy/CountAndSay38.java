public class CountAndSay38 {
  private String getNext(String sNum) {
    int len = sNum.length();
    char cur = sNum.charAt(0);
    int count = 1;
    StringBuilder ret = new StringBuilder();
    for (int i = 1; i < len; i++) {
      char next = sNum.charAt(i);
      if (next == cur) {
        count++;
      } else {
        addToRes(ret, cur, count);
        cur = next;
        count = 1;
      }
    }
    addToRes(ret, cur, count);
    return ret.toString();
  }

  void addToRes(StringBuilder ret, char val, int count) {
    ret.append(Integer.toString(count));
    ret.append(val);
  }

  public String countAndSay(int n) {
    String s = "1";
    for (int i = 2; i <= n; i++) {
      s = getNext(s);
    }
    return s;
  }
}
