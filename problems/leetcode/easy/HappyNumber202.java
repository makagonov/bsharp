public class HappyNumber202 {
  long getNext(long val) {
    long sum = 0;
    while(val > 0) {
      sum += (val % 10) * (val % 10);
      val /= 10;
    }
    return sum;
  }
  public boolean isHappy(int n) {
    long cur = n;
    for (int i = 0; i < 10; i++) {
      //System.out.println(cur);
      long next = getNext(cur);
      if (next == 1) {
        return true;
      }
      cur = next;
    }
    return false;
  }
}
