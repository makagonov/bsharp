import java.util.Scanner;

public class ProgressPie {
  final int RADUIS = 50;
  final int centerX = 50;
  final int centerY = 50;
  final double EPS = 1e-6;
  public ProgressPie() {}

  public boolean solve(int p, int x, int y) {
    x -= centerX;
    y -= centerY;

    if (p == 0) return false;
    if (Math.sqrt(x * x + y * y) + EPS > RADUIS) return false;
    if (p == 100) return true;
    int [] converted = convertToTopRightSegment(p, x, y);
    return solveTopRightSegment(converted[0], converted[1], converted[2]);
  }

  private int [] convertToTopRightSegment(int p, int x, int y) {
    if (x >= 0 && y >= 0) return new int []{p, x, y};
    if (x >= 0 && y <= 0) return new int []{p - 25, -y, -x};
    if (x <= 0 && y <= 0) return new int []{p - 50, -x, -y};
    return new int []{p - 75, y, -x};

  }

  private double getSegmentLength(int x, int y) {
    return Math.sqrt(x * x + y * y);
  }

  private boolean solveTopRightSegment(int p, int x, int y) {
    if (p <= 0) return false;
    if (p >= 25) return true;
    double pointAngle = Math.toDegrees(Math.asin(y / getSegmentLength(x, y)));
    double unfilledAngle = 90 - p * 360. / 100;
    return unfilledAngle - EPS < pointAngle;
  }

  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    int T = in.nextInt();
    ProgressPie progressPie = new ProgressPie();
    for (int i = 1; i <= T; i++) {
      int p = in.nextInt(), x = in.nextInt(), y = in.nextInt();
      boolean isBlack = progressPie.solve(p, x, y);
      System.out.println("Case #" + i + ": " + (isBlack ? "black" : "white"));
    }

  }
}
