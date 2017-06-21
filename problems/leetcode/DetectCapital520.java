public class DetectCapital520 {
  public boolean detectCapitalUse(String word) {
    int n = word.length();
    if (n == 0) return false;
    int numCap = 0, numSmall = 0;
    for (int i = 0; i < word.length(); i++) {
      char c = word.charAt(i);
      if (isCapital(c)) numCap++;
      else if (isSmall(c)) numSmall++;
      else return false;
    }
    if (numCap == n) return true;
    if (numSmall == n) return true;
    if (numCap == 1 && isCapital(word.charAt(0)))
      return true;

    return false;
  }

  boolean isCapital(char c) {
    return c >= 'A' && c <= 'Z';
  }

  boolean isSmall(char c) {
    return c >= 'a' && c <= 'z';
  }
}
