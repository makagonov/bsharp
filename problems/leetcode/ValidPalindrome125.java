public class ValidPalindrome125 {
  public boolean isPalindrome(String s) {
    if (s.length() <= 1) return true;
    int i = 0, j = s.length() - 1;
    while(i <= j) {
      int iVal = -1;
      while(i <= j && (iVal = convertAlphaNumeric(s.charAt(i))) < 0) {
        i++;
      }
      if (i > j) break;
      int jVal = -1;
      while(j >= i && (jVal = convertAlphaNumeric(s.charAt(j))) < 0) {
        j--;
      }
      if (j < i) break;
      if (iVal != jVal) return false;
      i++;
      j--;
    }
    return true;
  }

  int convertAlphaNumeric(char c) {
    if (c >= '0' && c <= '9') return c;
    if (c >= 'A' &&c <='Z') return c - 'A';
    if (c >= 'a' && c <= 'z') return c - 'a';
    return -1;
  }
}
