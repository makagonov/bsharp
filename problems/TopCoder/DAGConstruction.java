import java.util.*;

public class DAGConstruction {

  public int[] construct(int[] x) {
    int n = x.length;
    Node [] nodes = new Node[n];
    for (int i = 0; i < n; i++) {
      nodes[i] = new Node(i, x[i]);
    }
    
    List<Node> [] levels = new ArrayList[n + 1];
    for (int i = 0; i < levels.length; i++)
      levels[i] = new ArrayList<>();
    for (int i = 0; i < n; i++)
      levels[nodes[i].mustHaveConnections].add(nodes[i]);
    
    for (int numConnections = 1; numConnections <= n; numConnections++) {
      for (Node node : levels[numConnections]) {
        for (int level = 1; level < node.mustHaveConnections;level++) {
          for (Node candidate : levels[level]) {
            if (node.connections.size() == node.mustHaveConnections) {
              break;
            }
            HashSet<Integer> copy = new HashSet<>(node.connections);
            copy.addAll(candidate.connections);
            if (copy.size() <= node.mustHaveConnections) {
              node.connections.addAll(candidate.connections);
            }
          }
        }  
      }
    }
    for (Node node : nodes)
      if (node.connections.size() < node.mustHaveConnections)
        return new int []{-1};
    
    List<Integer> edgeList = new ArrayList<>();
    for (Node node : nodes) {
      for (Integer v : node.connections) {
        if (v != node.vertex) {
          edgeList.add(node.vertex);
          edgeList.add(v);
        }
      }
    }
    int [] result = new int[edgeList.size()];
    for (int i = 0; i < edgeList.size(); i++)
      result[i] = edgeList.get(i);
    return result;
  }
  
  class Node {
    int mustHaveConnections;
    int vertex;
    HashSet<Integer> connections;
    
    public Node(int v, int n) {
      this.mustHaveConnections = n;
      this.vertex = v;
      this.connections = new HashSet<>();
      this.connections.add(v);
    }
    
    @Override
    public String toString() {
      return vertex + " (" + mustHaveConnections + "): " + connections;
    }
  }

}
