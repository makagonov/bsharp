import java.util.*;

public class LFUCache460 {

  class DataNode {
    int frequency;
    int key;
    int value;
    
    DataNode next;
    DataNode prev;
    
    public DataNode(int key, int value) {
      this.frequency = 1;
      this.key = key;
      this.value = value;
    }
  }

  class FrequencyNode {
    int frequency;
    
    FrequencyNode next;
    FrequencyNode prev;
    
    DataNode dataHead;
    DataNode dataTail;
    
    public FrequencyNode(int frequency) {
      this.frequency = frequency;
    }
    
    boolean hasData() {
      return dataHead != null && dataTail != null;
    }
    
    void addData(DataNode node) {
      if (dataHead == null)
        dataHead = dataTail = node;
      else {
        node.next = dataHead;
        dataHead.prev = node;
        dataHead = node;
      }
    }
    
    DataNode removeData(DataNode node) {
      if (node.prev != null) node.prev.next = node.next;
      if (node.next != null) node.next.prev = node.prev;
      if (dataHead == node) {
        dataHead = node.next;
      }
      if (dataTail == node) {
        dataTail = node.prev;
      }
      node.next = node.prev = null;
      return node;
    }
    
    DataNode peek() {
      return dataTail;
    }
  }
  
  private int capacity;
  private Map<Integer, DataNode> dataMap;
  private Map<Integer, FrequencyNode> frequencyMap;
  FrequencyNode frequencyHead; // frequency list with most frequently used elements
  FrequencyNode frequencyTail; // frequency list with least frequently used elements
  
  public LFUCache460(int capacity) {
    this.capacity = capacity;
    this.dataMap = new HashMap<>(capacity);
    this.frequencyMap = new HashMap<>();
  }
  
  private FrequencyNode createFrequency(int frequency) {
    FrequencyNode newFN = new FrequencyNode(frequency);
    frequencyMap.put(frequency, newFN);
    //now need to put the node at the proper position
    if (frequencyHead == null)
      return frequencyHead = frequencyTail = newFN;
    
    if (frequency == 1) {
      //node with frequency = 1 will always be the tail
      frequencyTail.next = newFN;
      newFN.prev = frequencyTail;
      frequencyTail = newFN;
    } else {
      //placing the new node before the FrequencyNode with frequency = frequency - 1
      FrequencyNode neighbor = frequencyMap.get(frequency - 1);
      newFN.next = neighbor;
      newFN.prev = neighbor.prev;
      if (neighbor == frequencyHead) frequencyHead = newFN;
      else neighbor.prev.next = newFN;
      neighbor.prev = newFN;
    }
    return newFN;
  }
  
  private void removeFrequency(FrequencyNode fn) {
    frequencyMap.remove(fn.frequency);
    if (fn.next != null) fn.next.prev = fn.prev;
    if (fn.prev != null) fn.prev.next = fn.next;
    if (fn == frequencyHead) frequencyHead = frequencyHead.next;
    if (fn == frequencyTail) frequencyTail = frequencyTail.prev;
    fn.prev = fn.next = null;
  }
  
  private FrequencyNode getOrCreateFrequency(int frequency) {
    FrequencyNode existingFN = frequencyMap.get(frequency);
    if (existingFN != null) {
      return existingFN;
    }
    return createFrequency(frequency);
  }
  
  private void addDataNode(DataNode node) {
    FrequencyNode fn = getOrCreateFrequency(node.frequency);
    fn.addData(node);
  }
  
  private void removeDataNode(DataNode node) {
    FrequencyNode oldFN = getOrCreateFrequency(node.frequency);
    oldFN.removeData(node);
    if (!oldFN.hasData())
      removeFrequency(oldFN);
  }
  
  private void removeLFUKey() {
    DataNode lfuData = frequencyTail.peek();
    removeDataNode(lfuData);
    dataMap.remove(lfuData.key);
  }
  
  private void incrementNodeFrequency(DataNode node) {
    // making sure that FrequencyNode for (node.frequency + 1) is there in advance,
    // because FrequencyNode for (node.frequency) might get deleted by removeDataNode(node)  
    getOrCreateFrequency(node.frequency + 1); 
    removeDataNode(node);
    node.frequency++;
    addDataNode(node);
  }
  
  public int get(int key) {
    if (capacity == 0 || !dataMap.containsKey(key))
      return -1;
    DataNode node = dataMap.get(key);
    incrementNodeFrequency(node);
    return node.value;
  }
  
  public void set(int key, int value) {
    if (capacity == 0) return;
    if (dataMap.containsKey(key)) {
      DataNode node = dataMap.get(key);
      node.value = value;
      incrementNodeFrequency(node);
      return;
    }
    
    if (dataMap.size() >= capacity) {
      removeLFUKey();
    }
    DataNode newNode = new DataNode(key, value);
    dataMap.put(key, newNode);
    addDataNode(newNode);
  }
}
