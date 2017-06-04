public class FactorCombinations254 {
  public List<List<Integer>> getFactors(int n) {
    LinkedList<List<Integer>> res = rec(2, n);
    res.pollLast(); //last element is a list of size 1 with "n" itself
    return res;
  }

  LinkedList<List<Integer>> rec(int min, int n) {
    LinkedList<List<Integer>> res = new LinkedList<>();
    if (n < min) return res;
    for (int i = min; i < n; i++) {
      if (n % i == 0) {
        List<List<Integer>> other = rec(i, n / i);
        for (List<Integer> lst : other) {
          ((LinkedList)lst).addFirst(i);
          res.add(lst);
        }
      }
    }
    List<Integer> single = new LinkedList<>();
    single.add(n);
    res.add(single);
    return res;
  }
}
