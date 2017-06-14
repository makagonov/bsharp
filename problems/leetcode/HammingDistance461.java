public class HammingDistance461 {
  public int hammingDistance(int x, int y) {
    int diffCount = 0;
    for (int bit = 0; bit < 32; bit++) {
      if ((x & (1 << bit)) != (y & (1 << bit))) {
        diffCount++;
      }
    }
    return diffCount;
  }
}
