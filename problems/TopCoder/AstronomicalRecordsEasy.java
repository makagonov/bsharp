import java.util.*;

public class AstronomicalRecordsEasy {

  public int minimalPlanets(int[] A, int[] B) {
    if (A[0] > B[0])
      return minimalPlanets(B, A);
    int best = A.length + B.length;
    for (int i : A) {
      for (int j : B) {
        HashSet<Integer> numsA = new HashSet<>();
        for (int ii : A) numsA.add(ii * j);
        HashSet<Integer> numsB = new HashSet<>();
        for (int jj : B) numsB.add(jj * i);
        numsA.addAll(numsB);
        if (numsA.size() < best)
          best = numsA.size();
      }
    }
    return best;    
  }
}
