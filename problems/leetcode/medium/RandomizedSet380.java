public class RandomizedSet380 {
  private HashMap<Integer, Integer> valToPos;
  private HashMap<Integer, Integer> posToVal;
  private int size;
  Random rnd;

  /** Initialize your data structure here. */
  public RandomizedSet() {
    valToPos = new HashMap<>();
    posToVal = new HashMap<>();
    size = 0;
    rnd = new Random();
  }

  /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
  public boolean insert(int val) {
    if (valToPos.containsKey(val)) return false;
    valToPos.put(val, size);
    posToVal.put(size, val);
    size++;
    return true;
  }

  /** Removes a value from the set. Returns true if the set contained the specified element. */
  public boolean remove(int val) {
    if (!valToPos.containsKey(val)) return false;
    if (valToPos.size() == 1) {
      valToPos.clear();
      posToVal.clear();
      size = 0;
      return true;
    }

    int pos = valToPos.get(val);
    int lastVal = posToVal.get(size - 1);
    valToPos.put(lastVal, pos);
    posToVal.put(pos, lastVal);
    valToPos.remove(val);
    posToVal.remove(size - 1);
    size--;
    return true;
  }

  /** Get a random element from the set. */
  public int getRandom() {
    int pos = rnd.nextInt(size);
    return posToVal.get(pos);
  }
}

