import java.util.*;
import java.util.LinkedList;

public class AhoCorasick {
  
  static final int ALPHABET_SIZE = 256;
  Node pseudoRoot;
  Node root;
  
  class Node {
    Map<Character, Node> next;
    Map<Character, Node> go; // transition in automata from current node by character
    Node parent;
    Node suffixLink; // suffix link
    char pChar; // character that connects parent with the node
    TreeSet<Integer> patterns; // indices of patterns that end at this node
    
    public Node(){
      next = new HashMap<>(ALPHABET_SIZE);
      go = new HashMap<>(ALPHABET_SIZE);
      patterns = new TreeSet<>();
    }
    
    public Node(Node from, char byChar) {
      this();
      parent = from;
      pChar = byChar;
    }
  }
  
  public AhoCorasick() {
    pseudoRoot = new Node();
    root = new Node();
    root.suffixLink = pseudoRoot;
    root.parent = pseudoRoot;
    
    for (char c = 0; c < ALPHABET_SIZE; c++) {
      pseudoRoot.next.put(c, root);
    }
  }
  
  public void addPattern(String s, int patternNumber) {
    Node current = root;
    for (int i = 0; i < s.length(); i++) {
      char c = s.charAt(i);
      if (current.next.containsKey(c)) {
        current = current.next.get(c);
      } else {
        Node newNode = new Node(current, c);
        current.next.put(c, newNode);
        current = newNode;
      }
    }
    current.patterns.add(patternNumber);
  }
  
  private Node getSuffixLink(Node node) {
    if (node.suffixLink == null) 
      node.suffixLink = go(getSuffixLink(node.parent), node.pChar);
    return node.suffixLink;
  }
  
  private Node go(Node node, char c) {
    if (node.go.containsKey(c)) return node.go.get(c);
    if (node.next.containsKey(c)) {
      node.go.put(c, node.next.get(c));
    } else {
      node.go.put(c, go(getSuffixLink(node), c));
    }
    return node.go.get(c);
  }
  
  public void buildAutomata() {
    Queue<Node> queue = new LinkedList<>();
    queue.add(root);
    while(!queue.isEmpty()) {
      Node node = queue.poll();
      getSuffixLink(node);
      for (char c = 0; c < ALPHABET_SIZE; c++){
        Node nextNode = go(node, c);
        if (nextNode.suffixLink == null) {
          queue.add(nextNode);
        }
      }
      node.patterns.addAll(getSuffixLink(node).patterns);
    }
  }
  
  
  public static void main (String [] args) {
    AhoCorasick aho = new AhoCorasick();
    Scanner in = new Scanner(System.in);
    int n = Integer.parseInt(in.nextLine());
    for (int i = 0; i < n; i++) {
      String pattern = in.nextLine();
      aho.addPattern(pattern, i);
    }
    
    aho.buildAutomata();
    
    Node current = aho.root;
    while (true) {
      String line = in.nextLine();
      System.out.println("line: " + line);
      if (line.equals("STOP")) break;
      char c = line.charAt(0);
      current = aho.go(current, c);
      System.out.println(current.patterns);
    }
  }
}
