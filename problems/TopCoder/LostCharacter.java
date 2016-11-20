import java.util.*;

public class LostCharacter {

  public int[] getmins(String[] str) {
    int n = str.length;
    int [] result = new int [n];
    for (int i = 0; i < n; i++) {
      String [] tmpStr = new String[n];
      String s = str[i];
      String sMinPossible = replaceQuestionsWithChar(s, 'a'); 
      tmpStr[0] = sMinPossible;
      int nextPosInTemp = 1;
      for (int j = 0; j < n; j++)
        if (j != i)
          tmpStr[nextPosInTemp++] = replaceQuestionsWithChar(str[j], 'z');
      Arrays.sort(tmpStr);
      
      int minPosInSorted = n - 1; 
      for (int j = 0; j < n; j++)
        if (tmpStr[j].equals(sMinPossible)) {
          minPosInSorted = j;
          break;
        }
      result[i] = minPosInSorted;
    }
    return result;
  }
  
  public String replaceQuestionsWithChar(String s, char c) {
    char [] arr = s.toCharArray();
    for (int i = 0; i < arr.length; i++)
      if (arr[i] == '?')
        arr[i] = c;
    return new String(arr);
  }

}
