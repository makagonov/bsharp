import java.math.BigInteger;
import java.util.*;

public class GooseInZooDivTwo {
  static final int MOD = 1000000007;
  boolean [][] used;
  char [][] arr;
  int n;
  int m;
  int dist;
  
  void dfs(int i, int j) {
    used[i][j] = true;
    for (int k = 0; k < n; k++)
      for (int f = 0; f < m; f++)
        if (arr[k][f] == 'v' && !used[k][f] && getDistance(i, j, k, f) <= dist) {
          dfs(k, f);
        }
  }
  
  public int count(String[] field, int dist) {
    n = field.length;
    m = field[0].length();
    this.dist = dist;
    arr = new char[n][];
    for (int i = 0; i < n; i++) {
      arr[i] = field[i].toCharArray();
    }
    
    used = new boolean[n][m];
    
    int numBirds = 0;
    for (int i = 0; i < n; i++)
      for (int j = 0; j < m; j++)
        if (field[i].charAt(j) == 'v')
          numBirds++;

    if (numBirds == 0) return 0;

    int numGroups = 0;
    for (int i = 0; i < n; i++)
      for (int j = 0; j < m; j++) {
        if (used[i][j] || arr[i][j] == '.') continue;
        numGroups++;
        dfs(i, j);
      }
    
    return getSumCombinations(numGroups);
  }

  int getDistance(int i, int j, int k, int f) {
    return Math.abs(i - k) + Math.abs(j - f);
  }

  int getSumCombinations(int n) {
    BigInteger res = BigInteger.valueOf(n);
    BigInteger cur = BigInteger.valueOf(n);
    BigInteger modBI = BigInteger.valueOf(MOD);
    for (int i = 2; i <= n; i++) {
      cur = cur.multiply(BigInteger.valueOf(n - i + 1)).divide(BigInteger.valueOf(i));
      res = res.add(cur).mod(modBI);
    }
    return res.intValue();
  }
}
