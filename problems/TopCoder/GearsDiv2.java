public class GearsDiv2 {
  public int getmin(String s) {
    if (s.length() == 1) 
      return 0;
    // moving the start of the string to the first char that is not equal to the first char
    int start = 0;
    while (start < s.length() && s.charAt(start) == s.charAt(0))
      start++;

    // each gear has the same direction
    if (start == s.length()) 
      return (s.length() + 1) / 2;
    // moving the end of the string to the right-most char which is not equal to the last char
    int end = s.length() - 1;
    while(end >= 0 && s.charAt(end) == s.charAt(s.length() - 1))
      end--;

    // now can treat the string [start, end] as a non-circular string
    int cur = 1;
    int result = 0;
    for (int i = start + 1; i <= end; i++) {
      if (s.charAt(i) == s.charAt(i - 1)) {
        cur++;
      } else {
        result += cur / 2;
        cur = 1;
      }
    }
    // if the last group size is greater than 1, it would not be added
    // to the result in the loop. Need to process it outside the loop
    if (cur > 1) result += cur / 2;
    
    int lastGroupSize = s.length() - end - 1;
    // if the first and the last chars are equal, they are in the same group, 
    // so their groups can be merged and processed as a single group
    if (s.charAt(0) == s.charAt(s.length() - 1))
      return result + (start + lastGroupSize) / 2;

    // Otherwise, treat the groups separately, just like in a non-circular string    
    return result + start / 2 + lastGroupSize / 2;
  }
}
