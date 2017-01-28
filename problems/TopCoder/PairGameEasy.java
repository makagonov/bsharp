import java.util.*;

public class PairGameEasy {
  final int MAX_VAL = 1000;
  public String able(int a, int b, int c, int d) {
    boolean [][] used = new boolean[MAX_VAL + 1][MAX_VAL + 1];
    HashSet<Pair> [] s = new HashSet[2];
    for (int i = 0; i < 2; i++)
      s[i] = new HashSet<>();
    tryAdd(s[0], used, a, b);
    for (int cur = 0; ; cur = 1 - cur) {
      if (s[cur].size() == 0 || used[c][d]) 
        break;
      s[1 - cur].clear();
      
      for (Pair p : s[cur]) {
        tryAdd(s[1 - cur], used, p.a, p.a + p.b);
        tryAdd(s[1 - cur], used, p.a + p.b, p.b);
      }
    }
    return used[c][d] ? "Able to generate" : "Not able to generate";
  }
  
  void tryAdd(HashSet<Pair> set, boolean [][] used, int a, int b) {
    if (a > MAX_VAL || b > MAX_VAL) return;
    if (used[a][b]) return;
    used[a][b] = true;
    set.add(new Pair(a, b));
  }
  
  class Pair {
    int a;
    int b;
    public Pair(int a, int b) {
      this.a = a;
      this.b = b;
    }
  }

}
