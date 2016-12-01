import java.util.*;

public class TrafficCongestionDivTwo {

  public long theMinCars(int treeHeight) {
    if (treeHeight == 0) return 1;
    long [] dp = new long[treeHeight + 1];
    dp[0] = 1;
    
    for (int i = 1; i <= treeHeight; i++) {
      dp[i] = 1;
      for (int j = 0; j <= i - 2; j++)
        dp[i] += 2 * dp[j];
    }
    
    return dp[treeHeight];
  }

}
