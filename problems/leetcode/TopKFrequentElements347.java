import java.util.*;

public class TopKFrequentElements347 {
  public List<Integer> topKFrequent(int[] nums, int k) {
    int n = nums.length;
    Map<Integer, Integer> frequencies = new HashMap<>();
    for (int i : nums) {
      frequencies.put(i, frequencies.getOrDefault(i, 0) + 1);
    }
    LinkedList<Integer> [] cnt = new LinkedList[n + 1];
    for (Map.Entry<Integer, Integer> entry : frequencies.entrySet()) {
      int f = entry.getValue();
      if (cnt[f] == null)
        cnt[f] = new LinkedList<>();
      cnt[f].add(entry.getKey());
    }
    Integer []result = new Integer[k];
    int idx = 0;
    for (int i = n; i >= 1 && idx < k; i--) {
      if (cnt[i] == null) continue;
      for (int j : cnt[i]) {
        if (idx == k) break;
        result[idx++] = j;
      }
    }
    return Arrays.asList(result);
  }

  public static void main (String [] args) {
    System.out.println(new TopKFrequentElements347().topKFrequent(new int []{1,1,1,2,2,3}, 2));
  }
}