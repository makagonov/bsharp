public class RelativeRanks506 {
  public String[] findRelativeRanks(int[] nums) {
    int n = nums.length;
    Integer[] indexes = new Integer[n];
    for (int i = 0; i < n; i++)
      indexes[i] = i;
    Arrays.sort(indexes, (a, b) -> {
      return nums[a] > nums[b] ? -1 : 1;
    });
    String [] res = new String[n];
    for(int i = 0; i < n; i++) {
      res[indexes[i]] = getStringRank(i + 1);
    }
    return res;
  }

  String getStringRank(int pos) {
    if (pos == 1) return "Gold Medal";
    if (pos == 2) return "Silver Medal";
    if (pos == 3) return "Bronze Medal";
    return Integer.toString(pos);
  }
}
