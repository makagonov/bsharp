import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.StringTokenizer;

public class ProblemA {
    public static void main (String [] args) throws Exception {
        new ProblemA();
    }

    BufferedReader in;
    PrintWriter out;
    StringTokenizer st;

    public ProblemA() throws Exception {
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
        String word = in.readLine();
        HashSet<String> set = new HashSet<>();
        set.add(word);
        for (int i = 0; i < word.length(); i++) {
            word = move(word);
            set.add(word);
        }
        out.println(set.size());
    }

    String move(String s) {
        return s.charAt(s.length() - 1) + s.substring(0, s.length() - 1);
    }
}
