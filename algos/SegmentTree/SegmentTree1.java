/**
 * Dynamic Segment tree supporting two types of operations:
 * - setting an element at a given position to a given value - set(int pos, int val)
 * - getting range maximum - getMax(int l, int r)
 * 
 * http://informatics.mccme.ru/mod/statements/view3.php?id=8445&chapterid=3316
 */
class SegmentTree1 {
  class Node {
    Node left;
    Node right;
    int max;
    
    void recalcMax() {
      max = Integer.MIN_VALUE;
      if (left != null && left.max > max) max = left.max;
      if (right != null && right.max > max) max = right.max;
    }
  }
    
  Node root;
  final int L;
  final int R;
  
  public SegmentTree1(int l, int r) {
    root = new Node();
    this.L = l;
    this.R = r;
  }
  
  private int getMax(Node node, int tl, int tr, int l, int r) {
    if (node == null || l > r || l < tl || r > tr) return Integer.MIN_VALUE;
    if (l == tl && r == tr) {
      // max is maintained at the segment level, so can safely return it
      return node.max;
    }
    int mid = tl + (tr - tl) / 2;
    return Math.max(
        getMax(node.left, tl, mid, l, Math.min(mid, r)),
        getMax(node.right, mid + 1, tr, Math.max(mid + 1, l), r)
    );
  }
  
  private void set(Node node, int tl, int tr, int pos, int val) {
    if (pos < tl || pos > tr) return;
    if (tl == pos && tr == pos) {
      // reached the node for the position, updating max on it
      node.max = val;
      return;
    }
    int mid = tl + (tr - tl) / 2;
    // as update is made only for one position, creating only the nodes that we need 
    if (pos <= mid) {
      if (node.left == null) node.left = new Node();
      set(node.left, tl, mid, pos, val);
    } else {
      if (node.right == null) node.right = new Node();
      set(node.right, mid + 1, tr, pos, val);
    }
    // "bubbling up" the maximum to the higher levels of the tree 
    node.recalcMax();
  }
  
  public void set(int pos, int val) {
    set(root, L, R, pos, val);
  }
  
  public int getMax(int l, int r) {
    return getMax(root, L, R, l, r);
  }
}
