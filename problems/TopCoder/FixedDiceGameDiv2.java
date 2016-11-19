import java.util.*;

public class FixedDiceGameDiv2 {

  public double getExpectation(int a, int b) {
    int [] aliceOutcomes = new int[a + 1];
    int totalPossibleConditionalOutcomes = 0;
    for (int i = 1; i <= b; i++) {
      for (int j = i + 1; j <= a; j++) {
        aliceOutcomes[j]++;
        totalPossibleConditionalOutcomes++;
      }
    }
    double mean = 0.d;

    for (int i = 2; i <= a; i++) {
      if (aliceOutcomes[i] > 0) {
        mean += 1. * i * aliceOutcomes[i] / totalPossibleConditionalOutcomes;
      }
    }
    return mean;
  }

}
