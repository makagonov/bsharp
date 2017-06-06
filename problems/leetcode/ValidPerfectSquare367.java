public class ValidPerfectSquare367 {
  public boolean isPerfectSquare(int num) {
    if (num == 1) return true;
    long l = 2, r = num;
    while(l < r) {
      long mid = l + (r - l) / 2;
      long square = mid * mid;
      if (square < num) {
        l = mid + 1;
      } else if (square > num) {
        r = mid;
      } else {
        return true;
      }
    }
    return l * l == num;
  }
}
