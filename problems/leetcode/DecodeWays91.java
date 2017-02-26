public class Solution {
    public int numDecodings(String s) {
        int n = s.length();
        if (n == 0 || s.charAt(0) == '0') return 0;
        int [] dp = new int[n + 1];
        dp[0] = 1;
        for (int i = 1; i <= n; i++) {
            if (s.charAt(i - 1) != '0')
                dp[i] += dp[i - 1];
            if (i == 1) continue;
            int a2 = Integer.parseInt(s.charAt(i - 2) + "" + s.charAt(i - 1));
            if (a2 >= 10 && a2 <= 26)
                dp[i] += dp[i - 2];
            
            if (dp[i] == 0)
                break;
        }
        return dp[n];
    }
}
