import java.util.*;

public class BinPackingEasy {

  public int minBins(int[] item) {
    int n = item.length;
    boolean [] used = new boolean[n];
    int res = 0;
    while(true) {
      int bestI = -1, bestJ = -1, bestSum = 0;
      for (int i = 0; i < n; i++)
        if (!used[i])
          for (int j = i + 1; j < n; j++)
            if (!used[j]) {
              int candidateSum = item[i] + item[j];
              if (candidateSum <= 300 && candidateSum > bestSum) {
                bestSum = candidateSum;
                bestI = i;
                bestJ = j;
              }
            }
      if (bestI < 0) {
        // pair not found
        break;
      }
      used[bestI] = used[bestJ] = true;
      res++;
    }
    // the rest of the items cannot be paired
    for (int i = 0; i < n; i++)
      if (!used[i]) res++;
    
    return res;
  }

}
