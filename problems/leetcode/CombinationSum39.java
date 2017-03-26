import java.util.*;

public class CombinationSum39 {
  void rec(List<List<Integer>> acc, LinkedList<Integer> cur, int curSum, int[] a, int t) {
    if (curSum > t) return;
    if (curSum == t) {
      acc.add(new LinkedList<Integer>(cur));
      return;
    }
    int lastNumber = cur.size() > 0 ? cur.peekLast() : 0;
    for (int i : a) {
      if (i < lastNumber) continue;
      cur.addLast(i);
      rec(acc, cur, curSum + i, a, t);
      cur.removeLast();
    }
  }
  public List<List<Integer>> combinationSum(int[] candidates, int target) {
    List<List<Integer>> result = new LinkedList<>();
    rec(result, new LinkedList<>(), 0, candidates, target);
    return result;
  }
}
