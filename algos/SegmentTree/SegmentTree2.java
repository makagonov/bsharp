/**
 * Dynamic Segment tree supporting two types of operations:
 * - adding a given value to a given range - update(int l, int r, int add)
 * - getting range maximum - getMax(int l, int r)
 *
 * http://informatics.mccme.ru/mod/statements/view3.php?id=8445&chapterid=3328
 */
class SegmentTree2 {
  class Node {
    Node left;
    Node right;
    long max;
    long add;
    
    void push() {
      if (left == null) left = new Node();
      if (right == null) right = new Node();
      left.add += add;
      left.max += add;
      right.add += add;
      right.max += add;
      add = 0;
    }
  }
  
  Node root;
  final int L;
  final int R;
  
  public SegmentTree2(int l, int r) {
    this.L = l;
    this.R = r;
    root = new Node();
  }

  private void update(Node node, int tl, int tr, int l, int r, int add) {
    if (l > r) return;
    if (tl == l && tr == r) {
      // stopping at the segment instead of propagating the changes down to the leaves
      // preserving "max" for the entire sub-tree rooted at node
      node.add += add;
      node.max += add;
      return;
    }
    // time to propagate the changes from node to children
    node.push();
    int mid = tl + (tr - tl) / 2;
    update(node.left, tl, mid, l, Math.min(r, mid), add);
    update(node.right, mid + 1, tr, Math.max(l, mid + 1), r, add);
    // "bubbling up" the max for the upper levels of the tree
    node.max = Math.max(node.left.max, node.right.max);
  }

  private long getMax(Node node, int tl, int tr, int l, int r) {
    if (l > r || l < tl || r > tr) return Long.MIN_VALUE;
    if (l == tl && r == tr) {
      return node.max;
    }
    node.push();
    int mid = tl + (tr - tl) / 2;
    return Math.max(
        getMax(node.left, tl, mid, l, Math.min(r, mid)),
        getMax(node.right, mid + 1, tr, Math.max(l, mid + 1), r)
    );
  }
  
  public void update(int l, int r, int add) {
    update(root, L, R, l, r, add);
  }
  
  public long getMax(int l, int r) {
    return getMax(root, L, R, l, r);
  }
}
