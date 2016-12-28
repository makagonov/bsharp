import java.util.*;

public class PilingRectsDiv2 {

  public int getmax(int[] X, int[] Y, int limit) {
    boolean hasAny = false;
    int n = X.length;
    Rect [] r = new Rect[n];
    for (int i = 0; i < n; i++) {
      if (X[i] * Y[i] >= limit) {
        hasAny = true;
      }
      r[i] = new Rect(X[i], Y[i]);
    }
    if (!hasAny) return -1;
    int absoluteBest = 1;
    // trying every rectangle as the basement
    for (int i = 0; i < n; i++) {
      if(r[i].area() < limit) continue;
      Rect rect = r[i];
      boolean [] used = new boolean[n];
      used[i] = true;
      while(true) {
        int max = 0;
        int bestIndex = -1;
        for (int j = 0; j < n; j++) {
          if (used[j]) continue;
          Rect merged = rect.merge(r[j]);
          int mergedArea = merged.area();
          if (mergedArea > max) {
            max = mergedArea;
            bestIndex = j;
          }
        }
        if (bestIndex >= 0 && max >= limit) {
          used[bestIndex] = true;
          rect = rect.merge(r[bestIndex]);
        } else {
          break;
        }
      }
      int count = 0;
      for (int j = 0; j < n; j++)
        if (used[j])
          count++;
      if (count > absoluteBest)
        absoluteBest = count;
    }
    return absoluteBest;
  }
  
  class Rect {
    int width;
    int height;
    
    int area() {
      return width * height;
    }
    public Rect(int w, int h) {
      width = w;
      height = h;
    }
    
    Rect transpose() {
      return new Rect(height, width);
    }
    
    @Override
    public String toString() {
      return width + "x" + height;
    }
    
    Rect merge(Rect other) {
      Rect transpose = this.transpose();
      Rect r1 = new Rect(Math.min(width,  other.width), Math.min(height, other.height));
      Rect r2 = new Rect(Math.min(transpose.width,  other.width), Math.min(transpose.height, other.height));
      if (r1.area() > r2.area())
        return r1;
      return r2;
    }
  }

}
