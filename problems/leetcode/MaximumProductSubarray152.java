public class MaximumProductSubarray152 {
  int getResultForSegment(int[] a, int[] p, int[] pRev, int start, int end) {
    if (start == end) return a[start];
    p[start] = a[start];
    for (int i = start + 1; i <= end; i++) {
      p[i] = p[i - 1] * a[i];
    }
    if (p[end] > 0) {
      return p[end];
    }
    int firstNeg = -1, firstNegRev = -1;
    for (int i = start; i <= end; i++) {
      if (a[i] < 0) {
        firstNeg = i;
        break;
      }
    }
    int res1 = p[end] / p[firstNeg];
    pRev[end] = a[end];
    for (int i = end - 1; i >= start; i--) {
      pRev[i] = pRev[i + 1] * a[i];
    }

    for (int i = end; i >= start; i--) {
      if (a[i] < 0) {
        firstNegRev = i;
        break;
      }
    }

    int res2 = pRev[start] / pRev[firstNegRev];
    return Math.max(res1, res2);
  }

  public int maxProduct(int[] a) {
    int n = a.length;
    if (n == 1) return a[0];
    int[] p = new int[n];
    int[] pRev = new int[n];
    int best = Integer.MIN_VALUE;
    int start = -1, end = -1;
    for (int i = 0; i < n; i++) {
      best = Math.max(best, a[i]);
      if (a[i] == 0) {
        best = Math.max(best, getResultForSegment(a, p, pRev, Math.max(start, 0), Math.max(end, 0)));
        start = end = -1;
      } else {
        if (start < 0) start = i;
        end = i;
      }
    }
    if (end >= 0) {
      best = Math.max(best, getResultForSegment(a, p, pRev, start, end));
    }

    return best;
  }
}
