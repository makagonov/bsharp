public class SentenceSplitting {

  public int split(String sentence, int K) {
    String [] sp = sentence.split(" ");
    int numSpaces = sp.length - 1;
    if (K >= numSpaces) {
      int max = 0;
      for (String s : sp)
        if (s.length() > max)
          max = s.length();
      return max;
    }
    int [] intVals = new int [sp.length];
    for (int i = 0; i < sp.length; i++)
      intVals[i] = sp[i].length();
    
    for (int maxLength = 1; maxLength <= sentence.length(); maxLength++) {
      if (canSplit(intVals, maxLength, K)) {
        return maxLength;
      }
    }
    
    return sentence.length();
  }
  
  boolean canSplit(int [] a, int maxLen, int k) {
    boolean result = true;
    int curLen = a[0];
    for (int i = 1; i < a.length; i++) {
      if (curLen + a[i] + 1 > maxLen) {
        k--;
        curLen = a[i];
        if (curLen > maxLen) 
          return false;
      } else {
        curLen += a[i] + 1;
      }
      if (k < 0)
        return false;
    }
    return result;
  }

}
