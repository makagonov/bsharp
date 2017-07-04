public class HitCounter362 {
  private static final int SECONDS_WINDOW = 300;
  TreeMap<Integer, Integer> counts;
  /** Initialize your data structure here. */
  public HitCounter() {
    counts = new TreeMap<>();
  }

  /** Record a hit.
   @param timestamp - The current timestamp (in seconds granularity). */
  public void hit(int timestamp) {
    counts.put(timestamp, counts.getOrDefault(timestamp, 0) + 1);
  }

  /** Return the number of hits in the past 5 minutes.
   @param timestamp - The current timestamp (in seconds granularity). */
  public int getHits(int timestamp) {
    int range = Math.max(0, timestamp - SECONDS_WINDOW + 1);
    while(counts.size() > 0 && counts.firstKey() < range) {
      counts.pollFirstEntry();
    }
    int sum = 0;
    for (int i : counts.values()) {
      sum += i;
    }
    return sum;
  }
}

/**
 * Your HitCounter object will be instantiated and called as such:
 * HitCounter obj = new HitCounter();
 * obj.hit(timestamp);
 * int param_2 = obj.getHits(timestamp);
 */
