public class SubsetsII90 {
  public List<List<Integer>> subsetsWithDup(int[] a) {
    Arrays.sort(a);
    List<List<Integer>> result = new LinkedList<>();
    rec(a, new LinkedList<>(), new boolean[a.length], 0, result);
    return result;
  }

  void rec(int [] a, LinkedList<Integer> cur, boolean [] used, int pos, List<List<Integer>> acc) {
    if (pos == a.length) {
      acc.add(new LinkedList<>(cur));
      return;
    }
    //if there are consequtive repeated elements, like 3 3 3 3 3
    // then there will never be a gap between included elements, so if 2 elements are included,
    // those will be the first 2, if 3 - the first 3;
    // so if element is at position "pos", and equal to elements at position "pos - 1"
    // and a[pos - 1] was not included - then none of the same elements after that would be included
    boolean exclude = pos > 0 && a[pos] == a[pos - 1] && !used[pos - 1];
    if (!exclude) {
      cur.add(a[pos]);
      used[pos] = true;
      rec(a, cur, used, pos + 1, acc);
      cur.pollLast();
      used[pos] = false;
    }
    rec(a, cur, used, pos + 1, acc);
  }
}
