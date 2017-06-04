public class RepeatedDNASequences187 {
  class DNASolver {
    private static final int PRIME = 31;
    private final long [] hash;
    private final long [] powers;
    private final int size;
    private final String s;

    DNASolver(String s) {
      this.s = s;
      this.size = s.length();
      powers = computePowers();
      hash = computeStrHash();
    }
    long [] computePowers() {
      long [] powers = new long[size];
      powers[0] = 1L;
      for (int i = 1; i < size; i++)
        powers[i] = powers[i - 1] * PRIME;
      return powers;
    }
    long [] computeStrHash() {
      long [] hash = new long[size];
      long curHash = 0;
      for (int i = 0; i < size; i++) {
        int cVal = s.charAt(i) - 'A' + 1;
        curHash += cVal * powers[i];
        hash[i] = curHash;
      }
      return hash;
    }
    long getSubstringHash(int from, int to) {
      long ret = hash[to];
      if (from > 0) ret -= hash[from - 1];
      ret *= powers[size - from - 1];
      return ret;
    }
    List<String> getRepeatedSequences(int targetLen) {
      Map<Long, Integer> memo = new HashMap<>();
      Set<Integer> resIdx = new HashSet<>();
      for (int i = 0; i + targetLen <= size; i++) {
        long h = getSubstringHash(i, i + targetLen - 1);
        Integer firstPos = memo.get(h);
        if (firstPos == null) {
          memo.put(h, i);
          continue;
        }
        resIdx.add(firstPos);
      }
      List<String> res = new LinkedList<>();
      for (int i : resIdx) {
        res.add(s.substring(i, i + targetLen));
      }
      return res;
    }
  }
  public List<String> findRepeatedDnaSequences(String s) {
    if (s == null || s.length() <= 1) return new LinkedList<>();
    return new DNASolver(s).getRepeatedSequences(10);
  }
}
