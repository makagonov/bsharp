public class MonstersValley2 {

  public int minimumPrice(int[] dread, int[] price) {
    int n = dread.length;

    int bestPrice = Integer.MAX_VALUE;

    for (int mask = 0; mask < (1 << n); mask++) {
      int maskPrice = getMaskPrice(mask, dread, price);
      if (maskPrice >= 0 && bestPrice > maskPrice) {
        bestPrice = maskPrice;
      }
    }

    return bestPrice;
  }


  private int getMaskPrice(int mask, int[] dread, int[] price) {
    long heroTotalDread = 0;
    int totalCost = 0;
    for (int i = 0; i < dread.length; i++) {
      if ((mask & (1 << i)) > 0) {
        totalCost += price[i];
        heroTotalDread += dread[i];
      } else {
        if (dread[i] > heroTotalDread) {
          return -1; // invalid case, the monster will attack the hero
        }
      }
    }

    return totalCost;
  }
}
