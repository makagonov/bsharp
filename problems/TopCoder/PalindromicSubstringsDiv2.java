import java.util.*;

public class PalindromicSubstringsDiv2 {
  private final long P = 31L;
  private long [] primePowers;
  public int count(String[] S1, String[] S2) {
    String s = concatAll(S1, S2);
    String sReverse = new StringBuilder(s).reverse().toString();
    initPrimePowers(s.length());
    
    long [] hashS = buildHash(s);
    long [] hashReverse = buildHash(sReverse);
    
    int count = 0;
    for (int start1 = 0; start1 < s.length(); start1++) {
      for (int end1 = start1; end1 < s.length(); end1++) {
        long hash1 = hashS[end1];
        if (start1 > 0) hash1 -= hashS[start1 - 1];
        
        int start2 = s.length() - end1 - 1;
        int end2 = s.length() - start1 - 1;
        long hash2 = hashReverse[end2];
        if(start2 > 0) hash2 -= hashReverse[start2 - 1];
        
        if (areHashesEqual(hash1, start1, hash2, start2))
          count++;
      }
    }
    
    return count;
  }
  
  private void initPrimePowers(int len) {
    primePowers = new long[len];
    primePowers[0] = 1L;
    for (int i = 1; i < len; i++)
      primePowers[i] = primePowers[i - 1] * P;
  }
  
  private long[] buildHash(String s) {
    long [] hash = new long[s.length()];
    long h = 0;
    for (int i = 0; i < s.length(); i++) {
      h += primePowers[i] * (s.charAt(i) - 'a' + 1);
      hash[i] = h;
    }
    return hash;
  }
  
  private boolean areHashesEqual(long hash1, int power1, long hash2, int power2) {
    if (power1 == power2) 
      return hash1 == hash2;
    
    if (power1 < power2)
      return primePowers[power2 - power1] * hash1 == hash2;
    return primePowers[power1 - power2] * hash2 == hash1;
  }
  
  private String concatAll(String [] s1, String [] s2) {
    StringBuilder sb = new StringBuilder();
    for (String s : s1) sb.append(s);
    for (String s : s2) sb.append(s);
    return sb.toString();
  }

}
