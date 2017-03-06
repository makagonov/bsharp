import java.util.*;

public class PaintHouseII265 {
  final int oo = Integer.MAX_VALUE / 2;

  public int minCostII(int[][] cost) {
    int n = cost.length;
    if (n == 0) return 0;
    int k = cost[0].length;
    if (k == 0) return 0;
    int[][] dp = new int[n + 1][k];
    for (int i = 1; i <= n; i++)
      Arrays.fill(dp[i], oo);
    for (int i = 0; i < k; i++)
      dp[1][i] = cost[0][i];

    int[][] minCostIndexes = new int[n + 1][2];
    populateMinCostIndexes(minCostIndexes, dp[0], 0);
    populateMinCostIndexes(minCostIndexes, dp[1], 1);

    for (int i = 2; i <= n; i++) {
      for (int j = 0; j < k; j++) {
        dp[i][j] = getMinVal(minCostIndexes, dp[i - 1], i - 1, j) + cost[i - 1][j];
      }
      populateMinCostIndexes(minCostIndexes, dp[i], i);
    }
    int best = oo;
    for (int i : dp[n])
      if (i < best)
        best = i;
    return best;
  }

  int getMinIdx(int[] vals, int excludeIdx) {
    int minIdx = -1;
    for (int i = 0; i < vals.length; i++) {
      if (i == excludeIdx) continue;
      if (minIdx < 0 || vals[i] < vals[minIdx]) {
        minIdx = i;
      }
    }
    return minIdx;
  }

  void populateMinCostIndexes(int[][] minCostIndexes, int[] vals, int row) {
    int minIdx = getMinIdx(vals, -1);
    int nextMinIdx = getMinIdx(vals, minIdx);
    minCostIndexes[row][0] = minIdx;
    minCostIndexes[row][1] = nextMinIdx;
  }

  int getMinVal(int[][] minCostIndexes, int[] vals, int row, int exceptIdx) {
    int minIdx = minCostIndexes[row][0];
    if (minIdx != exceptIdx) return vals[minIdx];
    int nextMinIdx = minCostIndexes[row][1];
    return vals[nextMinIdx];
  }
}
