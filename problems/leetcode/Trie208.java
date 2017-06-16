public class Trie208 {
  class Node {
    boolean isWordEnding;
    Map<Character, Node> next;
    public Node() {
      next = new HashMap<>();
    }
  }
  Node root;
  /** Initialize your data structure here. */
  public Trie() {
    root = new Node();
  }

  /** Inserts a word into the trie. */
  public void insert(String word) {
    Node cur = root;
    for (int i = 0; i < word.length(); i++) {
      char c = word.charAt(i);
      if (!cur.next.containsKey(c)) {
        cur.next.put(c, new Node());
      }
      cur = cur.next.get(c);
    }
    cur.isWordEnding = true;
  }

  /** Returns if the word is in the trie. */
  public boolean search(String word) {
    Node cur = root;
    for (int i = 0; i < word.length(); i++) {
      char c = word.charAt(i);
      Node next = cur.next.get(c);
      if (next == null) return false;
      cur = next;
    }
    return cur.isWordEnding;
  }

  /** Returns if there is any word in the trie that starts with the given prefix. */
  public boolean startsWith(String prefix) {
    Node cur = root;
    for (int i = 0; i < prefix.length(); i++) {
      char c = prefix.charAt(i);
      Node next = cur.next.get(c);
      if (next == null) return false;
      cur = next;
    }
    return true;
  }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */
