public class ReverseWords151 {
  public String cleanupInput(String s) {
    s = s.trim();
    int n = s.length();
    boolean [] skip = new boolean[n];
    for (int i = 1; i < n; i++) {
      if (s.charAt(i) == ' ' && s.charAt(i - 1) == ' ') {
        skip[i] = true;
      }
    }
    int skipCount = 0;
    for (boolean b : skip)
      if (b)
        skipCount++;

    char [] ret = new char[n - skipCount];
    int i = 0;
    for (int j = 0; j < n; j++) {
      if (!skip[j])
        ret[i++] = s.charAt(j);
    }
    return new String(ret);
  }

  public String reverseWords(String s) {
    s = cleanupInput(s);
    int len = s.length();
    char [] res = new char[len];
    int j = len - 1;
    int curLen = 0;
    for (int i = 0;i < len; i++) {
      if (s.charAt(i) != ' ') {
        curLen++;
      } else {
        for (int k = 0; k < curLen; k++) {
          res[j - k] = s.charAt(i - k - 1);
        }
        j -= curLen;
        curLen = 0;
        res[j--] = ' ';
      }
    }
    for (int k = 0; k < curLen; k++) {
      res[j - curLen + k + 1] = s.charAt(len - curLen + k);
    }
    return new String(res);
  }  
}
