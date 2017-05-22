public class TwoSum1 {
  public int[] twoSum(int[] nums, int target) {
    int n = nums.length;
    Map<Integer, List<Integer>> map = new HashMap<>();
    for (int i = 0; i < n; i++) {
      List<Integer> indices = map.getOrDefault(nums[i], new ArrayList<>());
      indices.add(i);
      map.put(nums[i], indices);
    }
    System.out.println(map);
    for (int i = 0; i < nums.length; i++) {
      int needed = target - nums[i];
      List<Integer> indices = map.get(needed);
      if (indices == null) continue;
      if (indices.size() == 1 && indices.get(0) == i) continue;
      if (indices.size() == 1) return new int []{i, indices.get(0)};
      return new int []{indices.get(0), indices.get(1)};
    }
    return null;
  }
}
