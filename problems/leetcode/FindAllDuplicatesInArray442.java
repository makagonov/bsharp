public class FindAllDuplicatesInArray442 {
  public List<Integer> findDuplicates(int[] nums) {
    int n = nums.length;
    List<Integer> res = new ArrayList<>();
    for (int i = 0; i < n; i++) {
      int val = nums[i];
      if (val < 0) continue;
      if (val == i + 1) continue;
      if (nums[val - 1] < 0) continue;
      if (nums[val - 1] == val) {
        nums[val - 1] = -1;
        continue;
      }
      int tmp = nums[i];
      nums[i] = nums[val - 1];
      nums[val - 1] = tmp;
      i--;
    }
    for (int i = 0; i < n; i++)
      if (nums[i] < 0)
        res.add(i + 1);
    return res;
  }
}
