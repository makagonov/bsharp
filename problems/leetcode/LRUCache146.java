import java.util.*;

public class LRUCache146 {
  private Map<Integer, Node> map;
  private int capacity;
  Node head; // the most recently used element
  Node tail; // the least recently used element
  
  class Node {
    int key;
    int value;
    Node prev;
    Node next;
    public Node(int key, int value) {
      this.key = key;
      this.value = value;
    }
    
    @Override
    public String toString() {
      return key + "=" + value;
    }
  }
  
  public LRUCache146(int capacity) {
    this.capacity = capacity;
    map = new HashMap<>(capacity + 1);
  }
  
  private void removeKey(int key) {
    if (!map.containsKey(key))
      return;
    Node node = map.get(key);
    removeNode(node);
    map.remove(key);
  }
  
  private void removeNode(Node node) {
    if (node.prev != null) node.prev.next = node.next;
    else head = node.next;
    if (node.next != null) node.next.prev = node.prev;
    else tail = node.prev;
    
    node.prev = node.next = null;
  }
  
  private void addFirst(Node node) {
    if (head != null) {
      head.prev = node;
      node.next = head;
    }
    head = node;
    if (tail == null) tail = node;
  }
  
  private void updateNode(Node node) {
    removeNode(node);
    addFirst(node);
  }
  
  public void set(int key, int value) {
    if (map.containsKey(key)) {
      Node node = map.get(key);
      node.value = value;
      updateNode(node);
      return;
    }
    
    Node node = new Node(key, value);
    if (map.size() >= capacity) {
      removeKey(tail.key);
    }
    map.put(key, node);
    addFirst(node);
  }
  
  public int get(int key) {
    if (!map.containsKey(key))
      return -1;
    Node node = map.get(key);
    updateNode(node);
    return node.value;
  }
  
  public static void main (String [] args) {
    LRUCache146 lru = new LRUCache146(2);
    System.out.println("get 2 result: " + lru.get(2));
    lru.set(2, 6);
    System.out.println("get 1 result: " + lru.get(1));
    lru.set(1, 5);
    lru.set(1, 2);
    System.out.println("get 1 result: " + lru.get(1));
    System.out.println("get 2 result: " + lru.get(2));
  }
  
}
