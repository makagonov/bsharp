public class BestTimeToBuyAndSellStock121 {
  public int maxProfit(int[] prices) {
    int n = prices.length;
    if (n == 0) return 0;
    int best = 0;
    int min = prices[0];
    for (int i = 1; i < n; i++) {
      best = Math.max(best, prices[i] - min);
      if (prices[i] < min) min = prices[i];
    }
    return best;
  }
}
