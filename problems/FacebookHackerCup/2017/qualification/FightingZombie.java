import java.util.HashMap;
import java.util.Scanner;

public class FightingZombie {
  static final int MAX_ROLLS = 20;
  static final int [] POSSIBLE_DICE_SIDES = new int []{4, 6, 8, 10, 12, 20};

  public FightingZombie() throws Exception {
    Scanner in = new Scanner(System.in);
    int T = in.nextInt();
    HashMap<Integer, double[][]> allSidesDp = new HashMap<>();
    for (int sides : POSSIBLE_DICE_SIDES) {
      allSidesDp.put(sides, calculateProbabilitiesForDie(sides));
    }

    for (int t = 1; t <= T; t++) {
      int H = Integer.parseInt(in.next()), S = Integer.parseInt(in.next());
      Spell [] spells = new Spell[S];
      for (int i = 0; i < S; i++)
        spells[i] = new Spell(in.next());
      System.out.printf("Case #%d: %.6f\n", t, solve(spells, H, allSidesDp));
    }
  }

  double solve(Spell [] spells, int H, HashMap<Integer, double[][]> allSidesDp) throws Exception {
    double bestProbability = 0;
    for (Spell spell : spells) {
      double [][] dp = allSidesDp.get(spell.diceSides);
      double totalProb = 0;
      for (int sum = spell.numRolls; sum < dp[0].length; sum++) {
        if (sum + spell.z >= H) {
            totalProb += dp[spell.numRolls][sum];
        }
      }
      if (bestProbability < totalProb)
        bestProbability = totalProb;
    }
    if (bestProbability > 1) {
      System.err.println("foooo" + bestProbability);
    }
    return bestProbability;
  }

  public double[][] calculateProbabilitiesForDie(int sides) {
    // dp[i][j] is the probability of having sum j after i rolls
    double[][] dp = new double[MAX_ROLLS + 1][sides * MAX_ROLLS + 1];
    for (int i = 1; i <= sides; i++)
      dp[1][i] = 1. / sides;

    for (int rolls = 2; rolls <= MAX_ROLLS; rolls++) {
      for (int sum = rolls; sum < dp[rolls].length; sum++) {
        for (int curRollNumber = 1; curRollNumber <= sides && sum - curRollNumber > 0; curRollNumber++) {
          dp[rolls][sum] += dp[rolls - 1][sum - curRollNumber] * 1. / sides;
        }
      }
    }
    return dp;
  }


  class Spell {
    int numRolls;
    int diceSides;
    int z;

    public Spell(String token) {
      numRolls = Integer.parseInt(token.substring(0, token.indexOf("d")));
      String rest = token.substring(token.indexOf("d") + 1);
      for (int possibleDice : POSSIBLE_DICE_SIDES) {
        String diceStr = Integer.toString(possibleDice);
        if (rest.startsWith(diceStr)) {
          diceSides = possibleDice;
          rest = rest.substring(diceStr.length());
          break;
        }
      }
      if (rest.length() > 0) {
        z = Integer.parseInt(rest);
      }
    }

    @Override
    public String toString() {
      String ret = numRolls + "d" + diceSides;
      if (z == 0) return ret;
      return ret + (z > 0 ? "+" + z : "" + z);
    }
  }

  public static void main(String[] args) throws Exception {
    new FightingZombie();
  }
}
