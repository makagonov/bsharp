public class InterleavingString97 {

  public boolean isInterleave(String s1, String s2, String s3) {
    int n = s1.length(), m = s2.length(), k = s3.length();
    if (n + m != k) return false;
    if (n == 0) return s2.equals(s3);
    if (m == 0) return s1.equals(s3);

    return canCompose(s1.toCharArray(), 0, s2.toCharArray(), 0, s3.toCharArray(), new Boolean[n + 1][m + 1]);
  }

  private boolean canCompose(char [] s1, int pos1, char [] s2, int pos2, char [] s3, Boolean [][] memo) {
    int pos3 = pos1 + pos2;
    if (pos3 == s3.length) return true;
    if (memo[pos1][pos2] != null) {
      return memo[pos1][pos2];
    }

    boolean res = false;
    if (pos1 < s1.length && s1[pos1] == s3[pos3]) {
      if (canCompose(s1, pos1 + 1, s2, pos2, s3, memo)) {
        res = true;
      }

    }
    if (pos2 < s2.length && s2[pos2] == s3[pos3]) {
      if (canCompose(s1, pos1, s2, pos2 + 1, s3, memo)) {
        res = true;
      }
    }
    return memo[pos1][pos2] = res;
  }

}
