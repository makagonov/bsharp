import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class AndroidUnlockPatterns351 {
  int numberOfPatterns(int n, int m) {
    AtomicInteger count = new AtomicInteger(0);
    HashSet<Integer> used = new HashSet<>();
    for (int i = 1; i <= 9; i++) {
      used.add(i);
      rec(used, i, count, n, m);
      used.remove(i);
    }
    return count.intValue();
  }

  void rec(HashSet<Integer> used, int last, AtomicInteger count, int n, int m) {
    if (used.size() >= n && used.size() <= m) {
      count.addAndGet(1);
    }
    if (used.size() == m) {
      return;
    }
    int x = getX(last), y = getY(last);
    for (int i = 1; i <= 9; i++) {
      if (used.contains(i)) continue;
      int x1 = getX(i), y1 = getY(i);
      if (x == x1 && Math.abs(y - y1) > 1 || //same row
          y == y1 && Math.abs(x - x1) > 1 || //same col
          Math.abs(x - x1) > 1 && Math.abs(y - y1) > 1) { // diagonal ends
        int mustHave = getVal(x + (x1 - x) / 2, y + (y1 - y) / 2);
        if (used.contains(mustHave)) {
          used.add(i);
          rec(used, i, count, n, m);
          used.remove(i);
        }
      } else {
        used.add(i);
        rec(used, i, count, n, m);
        used.remove(i);
      }
    }
  }

  int getVal(int i, int j) {
    return i * 3 + j + 1;
  }

  int getX(int i) {
    return (i - 1) / 3;
  }

  int getY(int i) {
    return (i - 1) % 3;
  }
}

