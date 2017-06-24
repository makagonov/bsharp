public class ValidSquare593 {
  class Point {
    int x, y;
    Point(int [] p) {
      this.x = p[0];
      this.y = p[1];
    }

    @Override
    public int  hashCode() {
      return x * 100000 + y;
    }

    @Override
    public boolean equals(Object o) {
      Point other = (Point)o;
      return this.x == other.x && this.y == other.y;
    }

    public int squareDist(Point p) {
      int diff1 = this.x - p.x;
      int diff2 = this.y - p.y;
      return diff1 * diff1 + diff2 * diff2;
    }
  }
  public boolean validSquare(int[] p1, int[] p2, int[] p3, int[] p4) {
    HashSet<Point> points = new HashSet<>();
    points.add(new Point(p1));
    if (!points.add(new Point(p2))) return false;
    if (!points.add(new Point(p3))) return false;
    if (!points.add(new Point(p4))) return false;

    return rec(new int[4], new ArrayList<>(points), new boolean[4], 0);
  }

  boolean check(int [] indexes, ArrayList<Point> points) {
    Point p1 = points.get(indexes[0]);
    Point p2 = points.get(indexes[1]);
    Point p3 = points.get(indexes[2]);
    Point p4 = points.get(indexes[3]);
    HashSet<Integer> dists = new HashSet<>();
    dists.add(p1.squareDist(p2));
    if (dists.add(p1.squareDist(p4))) return false;
    if (dists.add(p2.squareDist(p3))) return false;
    if (dists.add(p3.squareDist(p4))) return false;
    return p1.squareDist(p3) == p2.squareDist(p4);
  }

  boolean rec(int [] indexes, ArrayList<Point> points, boolean [] used, int pos) {
    if (pos == indexes.length) {
      return check(indexes, points);
    }
    for (int i = 0; i < indexes.length; i++) {
      if (used[i]) continue;
      indexes[pos] = i;
      used[i] = true;
      boolean success = rec(indexes, points, used, pos + 1);
      if (success) return true;
      used[i] = false;
    }
    return false;
  }
}
