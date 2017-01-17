import java.util.*;

public class PackingBallsDiv2 {

  public int minPacks(int R, int G, int B) {
    int [] cols = new int[]{R, G, B};
    Arrays.sort(cols);
    int count = 0;
    //creating boxes of size 3
    count += cols[0];
    for (int i = 2; i >= 0; i--)
      cols[i] -= cols[0];

    for (int i = 1; i < 3; i++) {
      if (cols[i] >= 3) {
        count += cols[i] / 3;
        cols[i] = cols[i] % 3;
      }
    }
    //creating boxes of size 2
    cols = removeZeros(cols);
    Arrays.sort(cols);
    while (cols.length >= 2) {
      count += cols[0];
      cols[1] -= cols[0];
      cols[0] = 0;
      cols = removeZeros(cols);
    }
    if (cols.length > 0) {
      //only 1 color is left, making as many boxes of size 2 as we can
      //and if the number is odd, creating a box for the leftover
      //so at the end we'll have at most 1 box of size 1
      count += cols[0] / 2 + cols[0] % 2;
    }

    return count;
  }

  int [] removeZeros(int [] colors) {
    int n = 0;
    for (int i : colors)
      if (i > 0)
        n++;
    int [] ret = new int[n];
    int idx = 0;
    for (int i : colors)
      if (i > 0)
        ret[idx++] = i;
    return ret;
  }



}
