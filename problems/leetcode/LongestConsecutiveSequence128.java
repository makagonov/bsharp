public class LongestConsecutiveSequence128 {
  private static final Random rnd = new Random();
  public int longestConsecutive(int[] nums) {
    int n = nums.length;
    int [] p = new int[n];
    for (int i = 0; i < n; i++)
      p[i] = i;
    Map<Integer, Integer> positions = new HashMap<>();
    for (int i = 0; i < n; i++) {
      int val = nums[i];
      if (positions.containsKey(val)) {
        continue;
      }
      Integer prev = positions.get(val - 1);
      Integer next = positions.get(val + 1);
      if (prev != null) {
        union(p, i, prev);
      }
      if (next != null) {
        union(p, i, next);
      }
      positions.put(val, i);
    }
    for (int i = 0; i < n; i++)
      p[i] = find(p, p[i]);

    int [] counts = new int[n];
    for (int i = 0; i < n; i++) {
      counts[p[i]]++;
    }

    int max = 0;
    for (int i = 0; i < n; i++) {
      if (counts[i] > max) {
        max = counts[i];
      }
    }
    return max;
  }

  void union(int [] p, int x, int y) {
    int r = rnd.nextInt(2);
    int setX = find(p, x);
    int setY = find(p, y);
    if (r == 0) {
      p[setX] = setY;
    } else {
      p[setY] = setX;
    }
  }

  int find(int [] p, int x) {
    if (p[x] == x) return x;
    return p[x] = find(p, p[x]);
  }
}
