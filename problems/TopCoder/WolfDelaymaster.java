import java.util.*;

public class WolfDelaymaster {
  List<String> allWords = new ArrayList<>();

  public String check(String str) {
    char [] chars = "wolf".toCharArray();
    for (int i = 1; i <= 12; i++) {
      StringBuilder sb = new StringBuilder();
      for (char c : chars) {
        for (int j = 0; j < i; j++) {
          sb.append(c);
        }        
      }
      allWords.add(sb.toString());
    }
    return resultToStr(isValid(str));
  }

  boolean isValid(String str) {
    if (str.length() == 0) return true;
    if (str.length() < 4) return false;
    for (String word : allWords) {
      int index = str.indexOf(word);
      if (index < 0) continue;
      boolean result = true;
      if (index > 0) {
        result &= isValid(str.substring(0, index));
      }
      if (index + word.length() < str.length()) {
        result &= isValid(str.substring(index + word.length(), str.length()));
      }
      return result;

    }
    return false;
  }

  String resultToStr(boolean val) {
    return val ? "VALID" : "INVALID";
  }
}
