/**
 * Dynamic Segment tree supporting two types of operations:
 * - setting elements in the range to a given value - set(int l, int r, int color)
 * - getting the value of an element by position - get(int pos)
 *
 * http://informatics.mccme.ru/mod/statements/view3.php?id=8445&chapterid=3329 
 */
class SegmentTree3 {
  class Node {
    Node left;
    Node right;
    int color;

    void push() {
      if (left == null) left = new Node();
      if (right == null) right = new Node();
      if (color < 0) return;
      left.color = color;
      right.color = color;
      color = -1;
    }
  }
  
  Node root;
  final int L;
  final int R;

  public SegmentTree3(int l, int r) {
    this.L = l;
    this.R = r;
    root = new Node();
  }

  private void set(Node node, int tl, int tr, int l, int r, int color) {
    if (l > r) return;
    if (l == tl && r == tr) {
      node.color = color;
      return;
    }
    node.push();
    int mid = tl + (tr - tl) / 2;
    set(node.left, tl, mid, l, Math.min(r, mid), color);
    set(node.right, mid + 1, tr, Math.max(l, mid + 1), r, color);
  }

  private int get(Node node, int tl, int tr, int pos) {
    if (tl == pos && tr == pos) {
      return node.color;
    }
    node.push();
    int mid = tl + (tr - tl) / 2;
    if (pos <= mid) {
      return get(node.left, tl, mid, pos);
    }
    return get(node.right, mid + 1, tr, pos);
  }

  public void set(int l, int r, int color) {
    set(root, L, R, l, r, color);
  }

  public long get(int pos) {
    return get(root, L, R, pos);
  }
}
