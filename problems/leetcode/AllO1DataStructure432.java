import java.util.*;


public class AllO1DataStructure432 {
  class ValueNode {
    final int val;
    ValueNode prev;
    ValueNode next;
    ValueNode(int v) {
      this.val = v;
    }

    @Override
    public String toString() {
      return Integer.toString(val);
    }
  }

  ValueNode valuesHead;
  ValueNode valuesTail;
  HashMap<Integer, ValueNode> valuesMapping;
  HashMap<Integer, HashSet<String>> valueToKeysMapping;
  HashMap<String, Integer> keyValueMapping;
  /** Initialize your data structure here. */
  public AllOne() {
    valuesHead = null;
    valuesTail = null;
    valuesMapping = new HashMap<>();
    valueToKeysMapping = new HashMap<>();
    keyValueMapping = new HashMap<>();
  }

  private void addOrUpdateOrRemoveKey(String key, int diff) {
    Integer curValue = keyValueMapping.get(key);
    ValueNode curValueNode = curValue == null ? null : valuesMapping.get(curValue);
    int newValue = curValue == null ? 1 : curValue + diff;
    ensureValueNode(newValue, curValueNode);
    if (curValue != null) {
      HashSet<String> valueKeys = valueToKeysMapping.get(curValue);
      valueKeys.remove(key);
      if (valueKeys.size() == 0) {
        removeValueNode(curValueNode);
      }
    }
    if (newValue == 0) {
      keyValueMapping.remove(key);
      return;
    }
    keyValueMapping.put(key, newValue);
    ValueNode newValueNode = valuesMapping.get(newValue);
    valueToKeysMapping.get(newValue).add(key);
  }

  void removeValueNode(ValueNode node) {
    ValueNode prev = node.prev;
    ValueNode next = node.next;
    if (prev != null) prev.next = next;
    if (next != null) next.prev = prev;
    if (node == valuesHead) valuesHead = next;
    if (node == valuesTail) valuesTail = prev;
    valuesMapping.remove(node.val);
    valueToKeysMapping.remove(node.val);
  }

  void ensureValueNode(int newValue, ValueNode currentValue) {
    if (newValue == 0) return;
    if (valuesMapping.containsKey(newValue)) return;
    ValueNode newNode = new ValueNode(newValue);
    valuesMapping.put(newValue, newNode);
    valueToKeysMapping.put(newNode.val, new HashSet<>());
    if (newValue == 1) {
      if (valuesHead == null) {
        valuesHead = valuesTail = newNode;
      } else {
        valuesHead.prev = newNode;
        newNode.next = valuesHead;
        valuesHead = newNode;
      }
    } else {
      ValueNode next = currentValue.next;
      ValueNode prev = currentValue.prev;
      if (newValue < currentValue.val) {
        currentValue.prev = newNode;
        newNode.next = currentValue;
        newNode.prev = prev;
        if (prev != null) prev.next = newNode;
      } else {
        currentValue.next = newNode;
        newNode.prev = currentValue;
        newNode.next = next;
        if (next != null) next.prev = newNode;
        else valuesTail = newNode;
      }
    }
  }

  /** Inserts a new key <Key> with value 1. Or increments an existing key by 1. */
  public void inc(String key) {
    addOrUpdateOrRemoveKey(key, 1);
  }

  /** Decrements an existing key by 1. If Key's value is 1, remove it from the data structure. */
  public void dec(String key) {
    if (!keyValueMapping.containsKey(key)) return;
    addOrUpdateOrRemoveKey(key, -1);
  }

  /** Returns one of the keys with maximal value. */
  public String getMaxKey() {
    if (keyValueMapping.size() == 0) return "";
    HashSet<String> maxKeys = valueToKeysMapping.get(valuesTail.val);
    return maxKeys.iterator().next();
  }

  /** Returns one of the keys with Minimal value. */
  public String getMinKey() {
    if (keyValueMapping.size() == 0) return "";
    HashSet<String> minKeys = valueToKeysMapping.get(valuesHead.val);
    return minKeys.iterator().next();
  }
}

/**
 * Your AllOne object will be instantiated and called as such:
 * AllOne obj = new AllOne();
 * obj.inc(key);
 * obj.dec(key);
 * String param_3 = obj.getMaxKey();
 * String param_4 = obj.getMinKey();
 */
