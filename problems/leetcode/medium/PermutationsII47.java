public class PermutationsII47 {
  public List<List<Integer>> permuteUnique(int[] a) {
    Set<String> acc = new HashSet<>();
    int n = a.length;
    rec(acc, a, new int [n], new boolean[n], 0);
    List<List<Integer>> res = new LinkedList<>();
    for(String s : acc) res.add(resultStrToList(s));
    return res;
  }

  List<Integer> resultStrToList(String perm) {
    String [] sp = perm.split(",");
    List<Integer> ret = new LinkedList<>();
    for (String s : sp) ret.add(Integer.parseInt(s));
    return ret;
  }

  String permToStr(int [] a, int [] p) {
    String [] s = new String[a.length];
    for (int i = 0; i < a.length; i++) {
      s[i] = Integer.toString(a[p[i]]);
    }
    return String.join(",", s);
  }

  void rec(Set<String> acc, int [] a, int [] p, boolean [] used, int pos) {
    int n = a.length;
    if (pos == n) {
      acc.add(permToStr(a, p));
      return;
    }
    for (int i = 0; i < n; i++) {
      if (used[i]) continue;
      p[pos] = i;
      used[i] = true;
      rec(acc, a, p, used, pos + 1);
      used[i] = false;
    }
  }
}
