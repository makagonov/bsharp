import java.util.*;

public class WordSearchII212 {
  private static final int [] dx = new int []{-1, 0, 1, 0};
  private static final int [] dy = new int []{0, -1, 0, 1};

  public List<String> findWords(char[][] brd, String[] words) {
    if (words.length == 0) return new ArrayList<>();
    
    Arrays.sort(words, (a, b) -> {
      if (a.length() > b.length()) return -1;
      if (a.length() < b.length()) return 1;
      return a.compareTo(b);
    });

    Cell[][] board = boardToCells(brd);
    
    Set<String> found = new HashSet<>();
    Node root = new Node();
    for (String p : words) {
      addString(root, p);
    }
    
    Set<Character> firstChars = new HashSet<>();
    for (String p : words) firstChars.add(p.charAt(0));
    
    Set<Cell> visited = new HashSet<>();
    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board[0].length; j++) {
        char c = board[i][j].c;
        if (!firstChars.contains(c)) {
          continue;
        }
        Cell cell = board[i][j];
        visited.add(cell);
        rec(root.move(c), board, visited, cell, found);
        visited.remove(cell);
      }
    }
    return new ArrayList<>(found);
  }
  
  private static Cell[][] boardToCells(char [][] b) {
    int n = b.length, m = b[0].length;
    Cell [][] ret = new Cell[n][m];
    for (int i = 0; i < n; i++)
      for (int j = 0; j < m; j++)
        ret[i][j] = new Cell(i, j, b[i][j]);
    return ret;
  }

  private static void rec(Node node, Cell[][] board, Set<Cell> visited, Cell cur, Set<String> found) {
    if (node.pattern != null) found.add(node.pattern);
    for (Cell nb : getValidNeighbors(board, cur, visited)) {
      Node next = node.move(nb.c);
      if (next != null) {
        visited.add(nb);
        rec(next, board, visited, nb, found);
        visited.remove(nb);
      }
    }
  }
  
  private static List<Cell> getValidNeighbors(Cell[][] board, Cell cur, Set<Cell> visited) {
    List<Cell> result = new ArrayList<>();
    for (int i = 0; i < 4; i++) {
      int x = cur.x + dx[i], y = cur.y + dy[i];
      if (x < 0 || y < 0 || x >= board.length || y >= board[0].length) {
        continue;
      }
      Cell candidate = board[x][y];
      if (visited.contains(candidate)) continue;
      result.add(candidate);
    }
    return result;
  }

  private static void addString(Node root, String s) {
    Node cur = root;
    for (int i = 0; i < s.length(); i++) {
      char c = s.charAt(i);
      cur.next.putIfAbsent(c, new Node());
      cur = cur.next.get(c);
    }
    cur.pattern = s;
  }

  private static class Node {
    Map<Character, Node> next;
    String pattern;
    public Node() {
      next = new HashMap<>();
    }

    Node move(char c) {
      return next.get(c);
    }
  }

  private static class Cell {
    int x, y;
    char c;
    Cell(int x, int y, char c) {
      this.x = x;
      this.y = y;
      this.c = c;
    }
  }
  
  public static void main (String [] args) {
    String [] board = new String[]{"oaan","etae","ihkr","iflv"};
    char [][] b = new char[board.length][];
    for (int i = 0; i < b.length; i++)
      b[i] = board[i].toCharArray();
    System.out.println(new Solution().findWords(b, new String[]{"oath","pea","eat","rain", "oate","oat"}));
  }
}

