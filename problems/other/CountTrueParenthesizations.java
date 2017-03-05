// Problem: Counting Boolean Parenthesizations. You are given a boolean expression consisting of a string of the symbols 'true', 'false', 'and', 'or', and 'xor'. Count the number of ways to parenthesize the expression such that it will evaluate to true. 
// Ex. true xor (false or false and false)
// Ex. true xor (false or false) and false - 1 2 1


import java.io.*;
import java.util.*;

class CountTrueParenthesizations {
  public static void main(String[] args) {
    System.out.println(new CountTrueParenthesizations().findWays("true xor false") == 1);
    System.out.println(new CountTrueParenthesizations().findWays("true xor false or false and false") == 3);
    System.out.println(new CountTrueParenthesizations().findWays("true xor false and true") == 2);
    System.out.println(new CountTrueParenthesizations().findWays("true xor false or false") == 2);
    System.out.println(new CountTrueParenthesizations().findWays("true or true and false xor true") == 4);
  }

  int findWays(String s) {
    String[] sp = s.split(" ");
    int len = sp.length;
    //dp[i][j][0] indicates how many ways there are to obtain false expression for segment [i, j]
    //dp[i][j][1] indicates how many ways there are to obtain true expression for segment [i, j]
    //dp[i][j][2] is just a flag that this range has already been processed
    int[][][] dp = new int[len][len][3];
    for (int i = 0; i < len; i+= 2) {
      int res = Boolean.parseBoolean(sp[i]) ? 1 : 0;
      dp[i][i][res] = 1;
      dp[i][i][2] = 1;
    }
    rec(sp, dp, 0, len - 1);
    return dp[0][len - 1][1];
  }

  void rec(String[]sp, int[][][] dp, int start, int end) {
    //returning if this state was already processed before 
    if (dp[start][end][2] > 0) return;
    dp[start][end][2] = 1;

    for (int opIdx = 1; opIdx <= end - 1; opIdx+=2) {
      rec(sp, dp, start, opIdx - 1);
      rec(sp, dp, opIdx + 1, end);
      String operator = sp[opIdx];
      if (operator.equals("and")) {
        //for expression to be false, any side needs to be false
        dp[start][end][0] += dp[start][opIdx - 1][0] * dp[opIdx + 1][end][1] +
            dp[start][opIdx - 1][1] * dp[opIdx + 1][end][0] +
            dp[start][opIdx - 1][0] * dp[opIdx + 1][end][0];
        //for expression to be true, both sides need to be true
        dp[start][end][1] += dp[start][opIdx - 1][1] * dp[opIdx + 1][end][1];
      } else if (operator.equals("or")) {
        // for expression to be false, both sides need to be false
        dp[start][end][0] += dp[start][opIdx - 1][0] * dp[opIdx + 1][end][0];
        //for expression to be true, any side needs to be true
        dp[start][end][1] += dp[start][opIdx - 1][0] * dp[opIdx + 1][end][1] +
            dp[start][opIdx - 1][1] * dp[opIdx + 1][end][0] +
            dp[start][opIdx - 1][1] * dp[opIdx + 1][end][1];
      } else if (operator.equals("xor")) {
        // for expression to be false, both sides need to be the same
        dp[start][end][0] += dp[start][opIdx - 1][0] * dp[opIdx + 1][end][0] +
            dp[start][opIdx - 1][1] * dp[opIdx + 1][end][1];
        //for expression to be true, sides need to be different
        dp[start][end][1] += dp[start][opIdx - 1][0] * dp[opIdx + 1][end][1] +
            dp[start][opIdx - 1][1] * dp[opIdx + 1][end][0];
      }
    }
  }
}

