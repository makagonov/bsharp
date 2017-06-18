public class MaxConsecutiveOnesII487 {
  public int findMaxConsecutiveOnes(int[] a) {
    LinkedList<Integer> l = new LinkedList<>();
    int n = a.length;
    if (n == 0) return 0;
    int cur = 0;
    int best = 1;
    int zerosCount = 0;
    for (int i = 0; i < n; i++) {
      if (a[i] == 0) {
        zerosCount++;
        best = Math.max(best, cur + (l.isEmpty() ? 0 : l.peekFirst() + 1));
        if (cur > 0) addToList(l, cur, 2);
        addToList(l, 0, 2);
        cur = 0;
      } else {
        cur++;
      }
    }
    if (zerosCount == 1) return n;
    if (cur > 0) best = Math.max(best, cur + (l.isEmpty() ? 0 : l.peekFirst() + 1));
    return best;
  }

  void addToList(LinkedList<Integer> l, int val, int maxSize) {
    if (l.size() == maxSize) l.pollFirst();
    l.add(val);
  }
}
