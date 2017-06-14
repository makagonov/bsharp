public class ExcelSheetColumnTitle168 {
  public String convertToTitle(int n) {
    StringBuilder sb = new StringBuilder();
    while(n > 0) {
      n--;
      char c = (char)(n % 26 + 'A');
      sb.append(c);
      n /= 26;
    }
    return sb.reverse().toString();
  }
}
