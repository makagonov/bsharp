import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

public class ProblemC {
    public static void main (String [] args) throws Exception {
        new ProblemC();
    }

    BufferedReader in;
    PrintWriter out;
    StringTokenizer st;
    Random rnd;
    public ProblemC() throws Exception {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
        rnd = new Random();
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

    int  [] p;
    private void solve() throws Exception {
        int n = nextInt(), m = nextInt(), k = nextInt();
        p = new int [n];
        for (int i = 0; i < n; i++)
            p[i] = i;

        ArrayList<Integer>[] roads = new ArrayList[n];
        for (int i = 0; i < n; i++)
            roads[i] = new ArrayList<>();

        HashSet<Integer> capitals = new HashSet<>();
        for (int i = 0; i < k; i++)
            capitals.add(nextInt() - 1);

        for (int i = 0; i < m; i++) {
            int a = nextInt() - 1, b = nextInt() - 1;
            roads[a].add(b);
            union(a, b);
        }

        for (int i = 0; i < n; i++)
            find(i);

        int [] roadsPerCountry = new int[n];
        int [] citiesPerCountry = new int[n];
        for (int cap : capitals) {
            int pCap = p[cap];
            if (p[cap] != cap) {
                // making capitals roots of components, just for clarify
                for (int j = 0; j < n; j++)
                    if (p[j] == pCap)
                        p[j] = cap;
            }

            for (int j = 0; j < n; j++)
                if (p[j] == cap) {
                    roadsPerCountry[cap] += roads[j].size();
                    citiesPerCountry[cap]++;
                }
        }
        //making components for corresponding countries complete graphs
        int bestCapital = 0;
        for (int capital : capitals) {
            if (citiesPerCountry[capital] > citiesPerCountry[bestCapital]) {
                bestCapital = capital;
            }
        }

        //uniting all the free cities
        int last = -1;
        for (int i = 0; i < n; i++) {
            if (!capitals.contains(p[i])) {
                if (last >= 0)
                    union(last, i);
                last = i;
            }
        }
        for (int i = 0; i < n; i++)
            find(i);

        int roadsInFreeCities = 0;
        int freeCities = 0;
        for (int i = 0; i < n; i++) {
            if (!capitals.contains(p[i])) {
                roadsInFreeCities += roads[i].size();
                freeCities++;
            }
        }

        //connecting the free cities to the country with largest number of cities
        int result = getCompleteGraphEdgesCount(freeCities + citiesPerCountry[bestCapital])
                        - roadsPerCountry[bestCapital]
                        - roadsInFreeCities;

        // making the other countries complete graphs too
        for (int capital : capitals) {
            if (capital != bestCapital) {
                result += (getCompleteGraphEdgesCount(citiesPerCountry[capital]) - roadsPerCountry[capital]);
            }
        }
        out.println(result);
    }

    int getCompleteGraphEdgesCount(int n) {
        if (n <= 1) return 0;
        if (n == 2) return 1;
        n--;
        return (1 + n) * n / 2;
    }

    void union(int x, int y) {
        int rootx = find(x);
        int rooty = find(y);
        if (rnd.nextInt() % 2 > 0) {
            p[rootx] = p[rooty];
        } else {
            p[rooty] = p[rootx];
        }
    }

    int find(int x) {
        if (p[x] == x) return x;
        return p[x] = find(p[x]);
    }
}
