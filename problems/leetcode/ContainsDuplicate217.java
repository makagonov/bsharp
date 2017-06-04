public class ContainsDuplicate217 {
  public boolean containsDuplicate(int[] a) {
    int n = a.length;
    if (n == 0) return false;
    int rndPos = new Random().nextInt(n);
    Set<Integer> set = new HashSet<>();
    for (int i = rndPos; i >= 0; i--) {
      if (!set.add(a[i])) return true;
    }

    for (int i = rndPos + 1; i < n; i++) {
      if (!set.add(a[i])) return true;
    }
    return false;
  }
}
