import java.util.*;
import java.math.*;
import static java.lang.Math.*;

public class TheAirTripDivTwo {
	public int find(int[] flights, int fuel) {
    int curSum = 0;
    int i = 0;
    for (; i < flights.length; i++) {
      curSum += flights[i];
      if (curSum > fuel) {
        break;
      }
    }
    return i;
  }

}
