import java.util.*;

public class PowerOfThreeEasy {

  public String ableToGet(int x, int y) {
    if (x == 0 && y == 0) return getStringForBool(true);
    long [] steps = new long[20];
    steps[0] = 1;
    for (int i = 1; i < steps.length; i++) {
      steps[i] = steps[i - 1] * 3;
    }
    for(int mask = 0; mask < (1 << steps.length); mask++) {
      long testX = 0, testY = 0;
      for (int j = 0; j < steps.length; j++) {
        if ((mask & (1 << j)) == 0) {
          testX += steps[j];
        } else {
          testY += steps[j];
        }
        if (testX == x && testY == y)
          return getStringForBool(true);
      }
    }
    
    return getStringForBool(false);
  }
  
  String getStringForBool(boolean success) {
    return success ? "Possible" : "Impossible";
  }

}
