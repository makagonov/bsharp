import java.util.*;

public class JumpFurther {

  public int furthest(int N, int badStep) {
    boolean [] used = new boolean[1 + (1 + N) * N / 2];
    List<Integer> res = new ArrayList<>();
    res.add(0);
    used[0] = true;
    for (int i = 1; i <= N; i++) {
      List<Integer> newVals = new ArrayList<>();
      for (int j = i; j >= 1; j--) {
        int idx = res.size() - j;
        if (idx >= 0) {
          int newVal = res.get(idx) + i;
          if (newVal != badStep && !used[newVal])
            newVals.add(newVal);
        }
      }
      res.addAll(newVals);
    }
    return res.get(res.size() - 1);
  }

}
