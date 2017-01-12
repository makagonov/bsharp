import java.util.*;

public class AlphabetOrderDiv1 {

  public String isOrdered(String[] words) {
    int n = 26;
    boolean [][] before = new boolean[n][n];

    for (String s : words) {
      for (int i = 0; i < s.length(); i++) {
        int c1 = s.charAt(i) - 'a';
        for (int j = i + 1; j < s.length(); j++) {
          int c2 = s.charAt(j) - 'a';
          if (c1 == c2) continue;
          if (before[c2][c1]) return getResultFromBool(false);
          before[c1][c2] = true;
        }
      }
    }

    for (int k = 0; k < n; k++)
      for (int i = 0; i < n; i++)
        for (int j = 0; j < n; j++)
          before[i][j] |= (before[i][k] && before[k][j]);

    for (int i = 0; i < n; i++)
      for (int j = 0; j < n; j++)
        if (before[i][j] && before[j][i])
          return getResultFromBool(false);

    return getResultFromBool(true);
  }

  String getResultFromBool(boolean success) {
    return success ? "Possible" : "Impossible";
  }

}
