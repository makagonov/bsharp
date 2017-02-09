import java.util.*;

public class WordBreak139 {
  public boolean wordBreak(String s, List<String> wordDict) {
    int n = s.length();
    if (n == 0) return false;
    if (n == 1) return wordDict.contains(s);

    Node root = buildTrie(wordDict);
    List<Integer>[] pStartLengths = new LinkedList[n];
    for (int i = 0; i < n; i++)
      pStartLengths[i] = new LinkedList<>();

    for (int i = 0; i < n; i++) {
      Node cur = root;
      int len = 0;
      for (int j = i; j < n; j++) {
        char c = s.charAt(j);
        Node next = cur.go(c);
        if (next == null) break;
        len++;
        if (next.patternEnd) {
          pStartLengths[i].add(len);
        }
        cur = next;
      }
    }
    boolean[] can = new boolean[n + 1];
    can[0] = true;
    for (int i = 0; i < n; i++) {
      if (!can[i]) continue;
      for (int len : pStartLengths[i]) {
        can[i + len] = true;
      }
    }
    return can[n];
  }

  Node buildTrie(List<String> ps) {
    Node root = new Node();
    for (String p : ps) {
      Node cur = root;
      for (int i = 0; i < p.length(); i++) {
        char c = p.charAt(i);
        Node next = cur.go(c);
        if (next == null) {
          next = cur.add(c);
        }
        cur = next;
      }
      cur.patternEnd = true;
    }
    return root;
  }

  class Node {
    Map<Character, Node> next;
    boolean patternEnd;

    public Node() {
      next = new HashMap<>();
    }

    Node go(char c) {
      return next.get(c);
    }

    Node add(char c) {
      Node node = new Node();
      next.put(c, node);
      return node;
    }
  }
}
