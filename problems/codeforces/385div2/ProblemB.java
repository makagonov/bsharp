import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class ProblemB {
    public static void main (String [] args) throws Exception {
        new ProblemB();
    }

    BufferedReader in;
    PrintWriter out;
    StringTokenizer st;

    public ProblemB() throws Exception {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
        solve();
        out.close();
    }

    String next() throws Exception {
        if (st == null || !st.hasMoreElements())
            st = new StringTokenizer(in.readLine());
        return st.nextToken();
    }

    int nextInt() throws Exception { return Integer.parseInt(next()); }
    long nextLong() throws Exception { return Long.parseLong(next()); }
    double nextDouble() throws Exception {return Double.parseDouble(next()); }

    private void solve() throws Exception {
        int n = nextInt(), m = nextInt();
        char [][] piece = new char[n][];
        for (int i = 0; i < n; i++) {
            piece[i] = in.readLine().toCharArray();
        }

        boolean [][] skip = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            boolean hasX = false;
            for (int j = 0; j < m; j++)
                if (piece[i][j] == 'X') {
                    hasX = true;
                    break;
                }
            if (!hasX) {
                for (int j = 0; j < m; j++)
                    skip[i][j] = true;
            }
        }

        for (int j = 0; j < m; j++) {
            boolean hasX = false;
            for (int i = 0; i < n; i++)
                if (piece[i][j] == 'X') {
                    hasX = true;
                    break;
                }
            if (!hasX) {
                for (int i = 0; i < n; i++)
                    skip[i][j] = true;
            }
        }

        int countX = 0;
        int countNotSkipped = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (piece[i][j] == 'X')
                    countX++;
                if (!skip[i][j])
                    countNotSkipped++;
            }
        }
        if (countX == countNotSkipped) {
            out.println("YES");
        } else {
            out.println("NO");
        }
    }
}
