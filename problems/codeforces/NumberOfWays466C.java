import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Iterator;
import java.util.StringTokenizer;

public class NumberOfWays466C {
  BufferedReader in;
  PrintWriter out;
  StringTokenizer st;

  public NumberOfWays466C() throws IOException {
    in = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(System.out);
    out.println(solve());
    out.close();
  }

  public String next() throws IOException {
    if (st == null || !st.hasMoreElements())
      st = new StringTokenizer(in.readLine());
    return st.nextToken();
  }

  public int nextInt() throws IOException { return Integer.parseInt(next());  }
  public long nextLong() throws IOException { return Long.parseLong(next()); }
  public double nextDouble() throws IOException { return Double.parseDouble(next()); }



  public long solve() throws IOException {
    int n = nextInt();
    if (n < 3) return 0;

    int [] a = new int [n];
    for (int i = 0; i < n; i++)
      a[i] = nextInt();

    long totalSum = 0;
    for (int i = 0; i < n; i++)
      totalSum += a[i];

    SumPositionsHelper positionsLeftToRight = new SumPositionsHelper(n);
    SumPositionsHelper positionsRightToLeft = new SumPositionsHelper(n);

    if (Math.abs(totalSum) % 3 > 0)
      return 0;

    long neededSum = totalSum / 3;
    long sumLeftToRight = 0;
    long sumRightToLeft = 0;
    for (int i = 0; i < n; i++) {
      sumLeftToRight += a[i];
      sumRightToLeft += a[n - i - 1];
      if (sumLeftToRight == neededSum) {
        positionsLeftToRight.add(i);
      }
      if (sumRightToLeft == neededSum) {
        positionsRightToLeft.add(n - i - 1);
      }
    }

    positionsRightToLeft.reverse();

    long result = 0;
    int leftBound = 0;
    while(positionsLeftToRight.hasNext()) {
      int positionLeft = positionsLeftToRight.next();
      SumPositionsHelper.CountMatchingResult tempResult = positionsRightToLeft.countFittingPosition(positionLeft, leftBound);
      if (tempResult.count == 0)
        break;

      result += tempResult.count;
      leftBound = tempResult.position;
    }

    return result;
  }

  static class SumPositionsHelper implements Iterator<Integer>{
    class CountMatchingResult {
      int count;
      int position;

      public CountMatchingResult(int c, int p) {
        this.count = c;
        this.position = p;
      }
    }

    int [] positions;
    int length;
    int initialSize;
    int iteratorPos = 0;

    public SumPositionsHelper(int initialSize) {
      this.positions = new int[initialSize];
      this.initialSize = initialSize;
    }

    public void add(int position) {
      positions[length++] = position;
    }

    public void reverse() {
      for (int i = 0; i < length / 2; i++) {
        int tmp = positions[i];
        positions[i] = positions[length - i - 1];
        positions[length - i - 1] = tmp;
      }
    }

    public CountMatchingResult countFittingPosition(int pos, int fromIndex) {
      int l = fromIndex, r = length;
      while (l < r) {
        int mid = (l + r) / 2;
        if (positions[mid] <= pos + 1) {
          l = mid + 1;
        } else {
          r = mid;
        }

      }
      if (l < length && positions[l] > pos + 1)
        return new CountMatchingResult(length - l, l);
      return new CountMatchingResult(0, l);
    }

    @Override
    public boolean hasNext() {
      return iteratorPos < length;
    }

    @Override
    public Integer next() {
      return positions[iteratorPos++];
    }

    @Override
    public String toString() {
      return Arrays.toString(Arrays.copyOfRange(positions, 0, length));
    }
  }

  public static void main (String [] args) throws IOException {
    new NumberOfWays466C();
  }
}
