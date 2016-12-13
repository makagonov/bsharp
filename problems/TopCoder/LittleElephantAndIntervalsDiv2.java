import java.util.*;

public class LittleElephantAndIntervalsDiv2 {

  public int getNumber(int M, int[] L, int[] R) {
    BitSet init = new BitSet(M);
    for (int i = 0; i < M; i++)
      init.set(i, false);

    HashSet<BitSet> [] s = new HashSet[2];
    for (int i = 0; i < 2; i++)
      s[i] = new HashSet<>();
    s[0].add(init);
    
    int cur = 0;
    for (int i = 0; i < L.length; i++, cur = 1 - cur) {
      s[1 - cur].clear();
      for (BitSet bs : s[cur]) {
        BitSet copyWhite = (BitSet)bs.clone();
        BitSet copyBlack = (BitSet)bs.clone();
        copyWhite.set(L[i] - 1, R[i], false);
        copyBlack.set(L[i] - 1, R[i], true);
        s[1 - cur].add(copyWhite);
        s[1 - cur].add(copyBlack);
      }
    }
    return s[cur].size();
  }

}
