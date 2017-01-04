import java.util.*;

public class RabinKarp {
  private final int P = 31;
  private long [] primePowers;
  private long [] allPrefixHashes;
  private int textLength;
  
  class Hash {
    long hashValue;
    int primePower;
    public Hash(long hash, int power) {
      this.hashValue = hash;
      this.primePower = power;
    }
    
    @Override
    public int hashCode() {
      return (int)(hashValue % Integer.MAX_VALUE);
    }
    
    @Override
    public boolean equals(Object obj) {
      if (!(obj instanceof Hash)) return false;
      Hash o = (Hash)obj;

      //bringing two hashes to the same power
      long myHash = hashValue;
      long otherHash = o.hashValue;
      if (o.primePower > primePower)
        myHash *= primePowers[o.primePower - primePower];
      else if (o.primePower < primePower)
        otherHash *= primePowers[primePower - o.primePower];
      
      return myHash == otherHash;
    }
  }
  
  public RabinKarp(String text) {
    textLength = text.length();
    primePowers = new long[text.length() + 1];
    allPrefixHashes = new long[text.length()];
    
    primePowers[0] = 1;
    for (int i = 1; i <= text.length(); i++) {
      primePowers[i] = primePowers[i - 1] * P;
    }
    long hash = 0;
    for (int i = 0; i < text.length(); i++) {
      hash += (text.charAt(i) - 'a' + 1) * primePowers[i];
      allPrefixHashes[i] = hash;
    }
  }
  
  private Hash getSubstringHash(int i, int j) {
    long hash = allPrefixHashes[j];
    if (i > 0) hash -= allPrefixHashes[i - 1];
    return new Hash(hash, i);
  }
  
  private Hash getStringHash(String s) {
    long hash = 0L;
    for (int i = 0; i < s.length(); i++) {
      hash += (s.charAt(i) - 'a' + 1) * primePowers[i];
    }
    return new Hash(hash, 0);
  }
  
  public List<Integer> findPattern(String pattern) {
    Hash patternHash = getStringHash(pattern);
    List<Integer> positionsInText = new ArrayList<>();
    
    int patLen = pattern.length();
    if (patLen > textLength) {
      throw new IllegalArgumentException("given pattern length " + patLen + " exceeds text length " + textLength);
    }
    for (int i = 0; i + patLen < textLength; i++) {
      if (patternHash.equals(getSubstringHash(i, i + patLen - 1))) {
        positionsInText.add(i);
      }
    }
    return positionsInText;
  }
  
  public static void main (String [] args) {
    String text = "abcaaabababfbfdfdfbabfdf";
    String pattern = "fdf";
    System.out.println(new RabinKarp(text).findPattern(pattern));
  }
}
