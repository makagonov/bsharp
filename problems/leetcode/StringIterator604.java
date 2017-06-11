import java.util.*;

public class StringIterator604 {
  private char[] letters;
  private int [] counts;
  private int [] used;
  private int currentPos = 0;
  private int numLetters;
  public StringIterator604(String s) {
    int numLetters = 0;
    int n = s.length();
    for (int i = 0; i < n; i++) {
      char c = s.charAt(i);
      if (c < '0' || c > '9') {
        numLetters++;
      }
    }
    this.numLetters = numLetters;
    letters = new char[numLetters];
    counts = new int[numLetters];
    used = new int[numLetters];
    
    for (int i = 0, j = 0; i < n; i++) {
      char c = s.charAt(i);
      if (c < '0' || c > '9') {
        letters[j++] = c;
      }
    }
    int cur = 0;
    int j = 0;
    for (int i = 1; i < n; i++) {
      char c = s.charAt(i);
      if (c >= '0' && c <= '9') {
        cur = cur * 10 + (c - '0');
      } else {
        counts[j++] = cur;
        cur = 0;
      }
    }
    if (cur > 0) counts[j] = cur;
  }

  public char next() {
    if (currentPos >= numLetters) {
      return ' ';
    }
    char ret = letters[currentPos];
    used[currentPos]++;
    if (used[currentPos] == counts[currentPos]) {
      currentPos++;
    }
    return ret;
  }

  public boolean hasNext() {
    return currentPos < numLetters; 
  }
}
