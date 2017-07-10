public class ShoppingOffers638 {
  public int shoppingOffers(List<Integer> price, List<List<Integer>> special, List<Integer> needs) {
    return rec(price, special, needs, new HashMap<>());
  }

  int rec(List<Integer> price, List<List<Integer>> special, List<Integer> needs, Map<List<Integer>, Integer> memo) {
    if (memo.containsKey(needs)) return memo.get(needs);
    int n = price.size();

    int res = Integer.MAX_VALUE;
    for (List<Integer> offer : special) {
      boolean good = true;
      List<Integer> newNeeds = new ArrayList<>(n);
      for (int i = 0; i < n; i++) {
        int newVal = needs.get(i) - offer.get(i);
        if (newVal < 0) {
          good = false;
          break;
        }
        newNeeds.add(newVal);
      }
      if (good) res = Math.min(res, offer.get(n) + rec(price, special, newNeeds, memo));
    }

    int noOffers = 0;
    for (int i = 0; i < n; i++) {
      noOffers += price.get(i) * needs.get(i);
    }
    res = Math.min(res, noOffers);
    memo.put(needs, res);
    return res;
  }
}
