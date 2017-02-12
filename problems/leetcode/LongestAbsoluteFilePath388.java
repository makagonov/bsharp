public class LongestAbsoluteFilePath388 {
  public int lengthLongestPath(String input) {
    String[] sp = input.split("\n");
    Node root = new Node(-1);
    Node cur = root;
    int best = 0;
    for (String s : sp) {
      Node next = new Node(s);
      while (cur.level != next.level - 1) {
        cur = cur.p;
      }
      next.setParent(cur);
      if (next.isFile) {
        best = Math.max(best, next.pathLen);
      } else {
        cur = next;
      }
    }
    return best;
  }

  class Node {
    int level;
    Node p;
    boolean isFile;
    int pathLen;
    int len;

    public Node(int level) {
      this.level = level;
    }

    public Node(String s) {
      int l = 0;
      while (s.charAt(l) == '\t') {
        l++;
      }
      level = l;
      len = s.length() - level;
      isFile = s.contains(".");
    }

    void setParent(Node parent) {
      p = parent;
      pathLen = parent.pathLen + len;
      if (parent.pathLen > 0)
        pathLen++;
    }
  }
}
