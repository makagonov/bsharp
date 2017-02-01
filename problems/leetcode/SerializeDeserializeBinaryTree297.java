import java.util.Arrays;
import java.util.Iterator;

public class SerializeDeserializeBinaryTree297 {
  static class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
  }
  
  void serializeDFS(TreeNode t, StringBuilder sb) {
    if (t == null) {
      return;
    }
    sb.append(t.val);
    sb.append(",");
    serializeDFS(t.left, sb);
    sb.append(",");
    serializeDFS(t.right, sb);
  }
  
  public String serialize(TreeNode root) {
    if (root == null) return "";
    StringBuilder sb = new StringBuilder();
    serializeDFS(root, sb);
    return sb.toString();
  }

  void deserializeDFS(TreeNode t, Iterator<String> it) {
    if (!it.hasNext()) return;
    String leftStr = it.next();
    if (leftStr.length() > 0) {
      t.left = new TreeNode(Integer.parseInt(leftStr));
      deserializeDFS(t.left, it);
    }
    if (!it.hasNext()) return;
    String rightStr = it.next();
    if (rightStr.length() > 0) {
      t.right = new TreeNode(Integer.parseInt(rightStr));
      deserializeDFS(t.right, it);
    }
  }
  
  public TreeNode deserialize(String data) {
    Iterator<String> it = Arrays.asList(data.split(",")).iterator();
    if (data == null || data.length() == 0) return null;
    TreeNode root = new TreeNode(Integer.parseInt(it.next()));
    deserializeDFS(root, it);
    return root;
  }
  
  public static void main (String []args) {
    String s = "1,2,,5,,,3,6,,,";
    TreeNode root = new SerializeDeserializeBinaryTree297().deserialize(s);
    String t = new SerializeDeserializeBinaryTree297().serialize(root);
    System.out.println("t: " + t + " " + s.equals(t));
  }
}
