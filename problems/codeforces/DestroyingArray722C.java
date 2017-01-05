import java.io.*;
import java.util.*;


public class DestroyingArray722C {
  public static void main (String [] args) throws Exception {
    FastReader in = new FastReader();
    PrintWriter out = new PrintWriter(System.out);
    int n = in.nextInt();
    int [] a = in.readIntArray(n);
    DestroyingArray722C destroyingArray = new DestroyingArray722C(n, a);
    for (int i = 0; i < n; i++) {
      out.println(destroyingArray.destroyCell(in.nextInt() - 1));
    }
    out.close();
  }

  private final long [] precalcSum;
  // max "heap" of segments based on the segment sum
  private final TreeSet<Segment> sortBySumDescSegments;
  //set of segments sorted by the start
  private final TreeSet<Segment> sortByStartSegments;

  public DestroyingArray722C(int n, int [] numbers) {
    this.precalcSum = new long [n];
    precalcSum[0] = numbers[0];
    for (int i = 1; i < n; i++)
      precalcSum[i] = precalcSum[i - 1] + numbers[i];

    sortBySumDescSegments = new TreeSet<>((a, b) -> {
      if (a.sum == b.sum) return a.start - b.start;
      return a.sum < b.sum ? 1 : -1;
    });
    sortByStartSegments = new TreeSet<>((a, b) -> a.start - b.start);

    Segment initialSegment = new Segment(0, n - 1);
    sortByStartSegments.add(initialSegment);
    sortBySumDescSegments.add(initialSegment);
  }

  public long destroyCell(int cellNumber) {
    // finding the segment with the greatest "start", s.t. it is less or equal to cellNumber
    Segment belongsToSegment = sortByStartSegments.floor(new Segment(cellNumber, cellNumber));
    List<Segment> newSegments = new ArrayList<>();
    if (belongsToSegment.start != belongsToSegment.end) {
      if (belongsToSegment.start == cellNumber) {
        newSegments.add(new Segment(belongsToSegment.start + 1, belongsToSegment.end));
      } else if (belongsToSegment.end == cellNumber) {
        newSegments.add(new Segment(belongsToSegment.start, belongsToSegment.end - 1));
      } else {
        newSegments.add(new Segment(belongsToSegment.start, cellNumber - 1));
        newSegments.add(new Segment(cellNumber + 1, belongsToSegment.end));
      }
    }
    sortBySumDescSegments.remove(belongsToSegment);
    sortByStartSegments.remove(belongsToSegment);

    sortBySumDescSegments.addAll(newSegments);
    sortByStartSegments.addAll(newSegments);

    return sortBySumDescSegments.isEmpty() ? 0 : sortBySumDescSegments.first().sum;
  }

  private long getSum(int start, int end) {
    long result = precalcSum[end];
    if (start > 0) result -= precalcSum[start - 1];
    return result;
  }

  class Segment {
    int start;
    int end;
    long sum;
    public Segment(int start, int end) {
      this.start = start;
      this.end = end;
      this.sum = getSum(start, end);
    }

    @Override
    public String toString() {
      return "[" + (start + 1) + ", " + (end + 1)+ "](sum=" + sum + ")";
    }
  }

  static class FastReader {
    private BufferedReader in;
    private StringTokenizer st;
    FastReader() {
      in = new BufferedReader(new InputStreamReader(System.in));
    }

    FastReader(String fileName) throws Exception {
      in = new BufferedReader(new FileReader(fileName));
    }

    String next() throws Exception {
      if (st == null || !st.hasMoreTokens())
        st = new StringTokenizer(in.readLine());
      return st.nextToken();
    }

    int nextInt() throws Exception { return Integer.parseInt(next()); }
    long nextLong() throws Exception { return Long.parseLong(next()); }
    double nextDouble() throws Exception { return Double.parseDouble(next()); }
    int [] readIntArray(int n) throws Exception {
      int [] a = new int [n];
      for (int i = 0; i < n; i++)
        a[i] = nextInt();
      return a;
    }

  }

}
