import java.util.*;

public class SlimeXSlimonadeTycoon {

  public int sell(int[] morning, int[] customers, int stale_limit) {
    int n = morning.length;
    LinkedList<Day> ll = new LinkedList<>();
    int res = 0;
    for (int i = 0; i < n; i++) {
      ll.addLast(new Day(morning[i]));
      if (ll.size() > stale_limit) {
        ll.removeFirst();
      }

      int c = customers[i];
      for (Day d : ll) {
        if (d.numLeft < c) {
          c -= d.numLeft;
          d.numLeft = 0;
        } else {
          d.numLeft -= c;
          c = 0;
          break;
        }
      }
      res += (customers[i] - c);
    }
    return res;
  }

  class Day {
    int numLeft;
    public Day(int n) {
      numLeft = n;
    }
  }

}
