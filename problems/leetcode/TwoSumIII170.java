public class TwoSumIII170 {
  HashMap<Integer, Integer> counts;
  /** Initialize your data structure here. */
  public TwoSum() {
    counts = new HashMap<>();
  }

  /** Add the number to an internal data structure.. */
  public void add(int number) {
    counts.put(number, counts.getOrDefault(number, 0) + 1);
  }

  /** Find if there exists any pair of numbers which sum is equal to the value. */
  public boolean find(int value) {
    for (Map.Entry<Integer, Integer> entry : counts.entrySet()) {
      int needed = value - entry.getKey();
      if (needed == entry.getKey()) {
        if (entry.getValue() > 1) {
          return true;
        }
      } else if (counts.containsKey(needed)) {
        return true;
      }
    }
    return false;
  }
}

/**
 * Your TwoSum object will be instantiated and called as such:
 * TwoSum obj = new TwoSum();
 * obj.add(number);
 * boolean param_2 = obj.find(value);
 */
