public class PrimeSoccer {

  public double getProbability(int skillOfTeamA, int skillOfTeamB) {

    int numPeriods = 90 / 5;
    //storing all the possible prime number outcomes less than numPeriods = 18
    int[] primes = new int[]{2, 3, 5, 7, 11, 13, 17};

    double pA = skillOfTeamA / 100.;
    double pB = skillOfTeamB / 100.;

    //calculating binomial distribution's PMFs (Probability Mass Functions) for both teams
    //which might seem redundant, but doing this for clarity
    double[] allOutcomesA = new double[numPeriods + 1];
    double[] allOutcomesB = new double[numPeriods + 1];
    for (int i = 0; i <= numPeriods; i++) {
      int binom = getBinomialCoefficient(numPeriods, i);
      allOutcomesA[i] = binom * Math.pow(pA, i) * Math.pow(1 - pA, numPeriods - i);
      allOutcomesB[i] = binom * Math.pow(pB, i) * Math.pow(1 - pB, numPeriods - i);
    }
    //now all the probabilities in allOutcomesA add up to 1.0, same is true for allOutcomesB
    //calculating separate probabilities that team A scores a prime, and teamB scores a prime
    double scoresPrimeA = 0, scoresPrimeB = 0;
    for (int prime : primes) {
      scoresPrimeA += allOutcomesA[prime];
      scoresPrimeB += allOutcomesB[prime];
    }
    //there are 4 possible outcomes for the joint distribution
    //and only 1 of them does not fit us - when both teams don't score a prime
    return 1 - (1 - scoresPrimeA) * (1 - scoresPrimeB);
  }

  public static int getBinomialCoefficient(int n, int k) {
    double res = 1;
    for (int i = 1; i <= k; i++)
      res = res * (n - k + i) / i;
    return (int) (res + 0.01);
  }
}
