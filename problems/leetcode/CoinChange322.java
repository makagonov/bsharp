public class CoinChange322 {
  private static final int oo = Integer.MAX_VALUE / 2;
  public int coinChange(int[] coins, int amount) {
    int [] dp = new int[amount + 1];
    Arrays.fill(dp, oo);
    dp[0] = 0;
    for (int i = 1; i <= amount; i++) {
      for (int j = 0; j < coins.length; j++) {
        if (i - coins[j] >= 0) {
          dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
        }
      }
    }
    return dp[amount] == oo ? -1 : dp[amount];
  }
}
