import java.util.*;

public class ORSolitaireDiv2 {

  public int getMinimum(int[] numbers, int goal) {
    //first, leaving only numbers that can actually be used in moves
    ArrayList<Integer> validNumbers = new ArrayList<>();
    for (int i : numbers) {
      if ((i | goal) == goal)
        validNumbers.add(i);
    }
    //next, trying to find the largest subset of valid numbers, s.t. they don't OR to goal
    int best = 0;
    for (int mask = 1; mask < (1 << validNumbers.size()); mask++) {
      int numPositiveBits = Integer.bitCount(mask); 
      if (numPositiveBits <= best)
        continue;
      
      int testNum = 0;
      for (int i = 0; i < validNumbers.size(); i++) {
        if ((mask & (1 << i)) > 0) {
          testNum |= validNumbers.get(i);
        }
      }
      if (testNum != goal) {
        best = numPositiveBits;
      }
    }
    return validNumbers.size() - best;
  }

}
