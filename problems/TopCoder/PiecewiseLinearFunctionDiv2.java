import java.util.*;

public class PiecewiseLinearFunctionDiv2 {

  public int[] countSolutions(int[] Y, int[] query) {
    int n = Y.length, m = query.length;
    
    Line [] lines = new Line[n - 1];
    for (int i = 0; i + 1 < n; i++) {
      lines[i] = new Line(i + 1, Y[i], i + 2, Y[i + 1]);
    }
    
    int [] result = new int[m];
    //first, processing values with infinite solutions
    for (int i = 0; i < m; i++) {
      boolean foundHorizontalLine = false;
      for (int j = 0; j + 1 < n; j++) {
        if (Y[j] == Y[j + 1] && Y[j] == query[i]) {
          foundHorizontalLine = true;
        }
      }
      if (foundHorizontalLine)
        result[i] = -1;
    }
    for (int i = 0; i < m; i++) {
      HashSet<Double> roots = new HashSet<>();
      if (result[i] < 0) continue;
      for (int j = 0; j < lines.length; j++) {
        Double root = lines[j].findX(query[i]);
        if (root != null) {
          roots.add(root);
        }
      }
      result[i] = roots.size();
    }
    
    return result;
  }
  
  class Line {
    double a;
    double b;
    long x1, x2, y1, y2;
    
    public Line(int x1, int y1, int x2, int y2) {
      this.x1 = x1;
      this.y1 = y1;
      this.x2 = x2;
      this.y2 = y2;
      this.a = 1.*(y2 - y1) / (x2 - x1);
      this.b = y1 - a*x1;
    }
    
    public Double findX(long y) {
      double ret = (y - b) / a;
      if (ret < x1 || ret > x2) return null;
      return ret;
    }
    
    @Override
    public String toString() {
      return a + " * x " + (b < 0 ? "- " + Math.abs(b) : "+ " + b);
    }
  }

}
