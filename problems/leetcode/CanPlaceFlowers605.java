public class CanPlaceFlowers605 {
  public boolean canPlaceFlowers(int[] a, int n) {
    int maxPlants = 0;
    int cur = 0;
    for (int i = 0; i < a.length;) {
      if (a[i] == 0) {
        cur++;
        i++;
      } else {
        cur--;
        if (cur > 0) maxPlants += cur / 2 + cur % 2;
        cur = 0;
        i += 2;
      }
    }
    maxPlants += cur / 2 + cur % 2;
    return maxPlants >= n;
  }
}
