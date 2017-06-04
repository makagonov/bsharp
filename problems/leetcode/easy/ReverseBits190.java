public class ReverseBits190 {
  // you need treat n as an unsigned value

  public int reverseBits(int n) {
    int res = 0;
    for (int bit = 0; bit < 32; bit++) {
      if ((n & (1 << bit)) != 0) {
        res |= (1 << (31 - bit));
      }
    }
    return res;
  }
}
