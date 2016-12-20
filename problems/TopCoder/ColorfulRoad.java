import java.util.*;

public class ColorfulRoad {
  final int MAX_VALUE = Integer.MAX_VALUE / 5;
  public int getMin(String road) {
    
    int n = road.length();
    int [] d = new int [n];
    Arrays.fill(d, MAX_VALUE);
    d[0] = 0;
    for (int i = 0; i < n; i++) {
      if (d[i] == MAX_VALUE) continue;
      char c1 = road.charAt(i);
      char nextChar = getNextColor(c1);
      for (int len = 1; i + len < n; len++) {
        if (road.charAt(i + len) == nextChar) {
          d[i + len] = Math.min(d[i + len], d[i] + len * len);
        }
      }
    }
    
    return d[n - 1] == MAX_VALUE ? -1 : d[n - 1];
  }
  
  char getNextColor(char c) {
    switch(c) {
    case 'R': return 'G';
    case 'G': return 'B';
    default: return 'R';
    }
    
  }

}
