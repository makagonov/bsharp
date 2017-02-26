public class Solution {
  public List<List<Integer>> subsets(int[] nums) {
    List<List<Integer>> ret = new ArrayList<>();
    int n = nums.length;
    for (int mask = 0; mask < (1 << n); mask++) {
      List<Integer> lst = new ArrayList<>();
      for (int j = 0; j < n; j++)
        if ((mask & (1 << j)) > 0)
          lst.add(nums[j]);
      ret.add(lst);
    }
    return ret;
  }
}
