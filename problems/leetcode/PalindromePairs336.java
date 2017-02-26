import java.util.*;

public class Solution {
  final int MAX_POWER = 100;
  final int PRIME = 31;
  final long[] powers;

  public Solution() {
    powers = new long[MAX_POWER + 1];
    powers[0] = 1L;
    for (int i = 1; i <= MAX_POWER; i++) {
      powers[i] = powers[i - 1] * PRIME;
    }
  }

  public List<List<Integer>> palindromePairs(String[] words) {
    int n = words.length;
    List<List<Integer>> result = new ArrayList<>();
    if (n == 0) return result;
    Word[] w = new Word[n];
    for (int i = 0; i < n; i++)
      w[i] = new Word(words[i]);
    for (int i = 0; i < n; i++)
      for (int j = 0; j < n; j++) {
        if (i == j) continue;
        if (isPalindrome(w[i], w[j])) {
          result.add(Arrays.asList(i, j));
        }
      }
    return result;
  }

  boolean isPalindrome(Word l, Word r) {
    if (l.len == 0) return r.isPrefixPalindrome(r.len);
    if (r.len == 0) return l.isPrefixPalindrome(l.len);
    if (l.len == r.len)
      return l.getPrefixHash(l.len) == r.getRevPrefixHash(r.len);
    if (l.len > r.len) {
      if (l.getPrefixHash(r.len) != r.getRevPrefixHash(r.len))
        return false;
      return l.isSuffixPalindrome(l.len - r.len);
    }
    if (l.getPrefixHash(l.len) != r.getRevPrefixHash(l.len))
      return false;
    return r.isPrefixPalindrome(r.len - l.len);
  }

  class Word {
    int len;
    long[] prefixHash;
    long[] revPrefixHash;

    Word(String s) {
      len = s.length();
      prefixHash = new long[len];
      revPrefixHash = new long[len];
      long hash = 0L, revHash = 0L;
      for (int i = 0; i < len; i++) {
        hash += (s.charAt(i) - 'a' + 1) * powers[i];
        revHash += (s.charAt(len - i - 1) - 'a' + 1) * powers[i];
        prefixHash[i] = hash;
        revPrefixHash[i] = revHash;
      }
    }

    long getPrefixHash(int prefixLen) {
      return powers[MAX_POWER] * prefixHash[prefixLen - 1];
    }

    long getSuffixHash(int suffixLen) {
      if (suffixLen == len) return getPrefixHash(len);
      return powers[MAX_POWER - len + suffixLen] * (prefixHash[len - 1] - prefixHash[len - suffixLen - 1]);
    }

    long getRevPrefixHash(int prefixLen) {
      return powers[MAX_POWER] * revPrefixHash[prefixLen - 1];
    }

    long getRevSuffixHash(int suffixLen) {
      if (suffixLen == len) return getRevPrefixHash(len);
      return powers[MAX_POWER - len + suffixLen] * (revPrefixHash[len - 1] - revPrefixHash[len - suffixLen - 1]);
    }

    boolean isSuffixPalindrome(int suffixLen) {
      if (suffixLen == 1) return true;
      return getSuffixHash(suffixLen) == getRevPrefixHash(suffixLen);
    }

    boolean isPrefixPalindrome(int prefixLen) {
      if (prefixLen == 1) return true;
      return getPrefixHash(prefixLen) == getRevSuffixHash(prefixLen);
    }
  }
}

