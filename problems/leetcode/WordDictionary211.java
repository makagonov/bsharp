public class WordDictionary211 {

  class Node {
    Map<Character, Node> next;
    boolean end;
    public Node() {
      this.next = new HashMap<>();
    }
  }

  Node root;

  public WordDictionary() {
    root = new Node();
  }

  public void addWord(String s) {
    Node node = root;
    for (int i = 0; i < s.length(); i++) {
      char c = s.charAt(i);
      Node next = node.next.get(c);
      if (next == null) next = new Node();
      node.next.put(c, next);
      node = next;
    }
    node.end = true;
  }

  boolean searchDFS(String s, int pos, Node node) {
    if (node == null) return false;
    if (pos == s.length()) return node.end;
    char c = s.charAt(pos);
    if (c != '.') return searchDFS(s, pos + 1, node.next.get(c));
    for (char c2 : node.next.keySet()) {
      if (searchDFS(s, pos + 1, node.next.get(c2))) {
        return true;
      }
    }
    return false;
  }

  public boolean search(String s) {
    return searchDFS(s, 0, root);
  }
}

