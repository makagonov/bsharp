public class ShortestPalindrome214 {
  public String shortestPalindrome(String s) {
    int n = s.length();
    if (n == 0) return s;
    String revS = new StringBuilder(s).reverse().toString();
    long [] primePowers = getPrimePowers(n, 31L);
    long [] hash = getStringHash(primePowers, s);
    long [] revHash = getStringHash(primePowers, revS);
    if (hash[n - 1] == revHash[n - 1]) return s; //already a palindrome

    for (int i = n - 2, j = 1; i > 0; i--, j++) {
      long prefixHash = hash[i];
      long prefixRevHash = revHash[n - 1] - revHash[j - 1];
      if (areHashesEqual(primePowers, prefixHash, 0, prefixRevHash, j)) {
        return prependPrefix(j, s);
      }
    }
    return prependPrefix(n - 1, s);
  }

  boolean areHashesEqual(long [] powers, long h1, int p1, long h2, int p2) {
    int n = powers.length;
    if (p1 > p2) h2 *= powers[p1 - p2];
    else if (p2 > p1) h1 *= powers[p2 - p1];
    return h1 == h2;
  }

  String prependPrefix(int numChars, String s) {
    return new StringBuilder(s.substring(s.length() - numChars))
        .reverse()
        .append(s)
        .toString();
  }

  long [] getPrimePowers(int n, long prime) {
    long [] p = new long[n];
    p[0] = 1;
    for (int i = 1; i < n; i++)
      p[i] = p[i - 1] * prime;
    return p;
  }

  long [] getStringHash(long [] powers, String s) {
    int n = s.length();
    long [] ret = new long[n];
    long hash = 0;
    for (int i = 0; i < n; i++) {
      hash += powers[i] * (s.charAt(i) - 'a' + 1);
      ret[i] = hash;
    }
    return ret;
  }
}
