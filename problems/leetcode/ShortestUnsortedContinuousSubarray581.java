public class ShortestUnsortedContinuousSubarray581 {
  public int findUnsortedSubarray(int[] a) {
    int n = a.length;
    if (n <= 1) return 0;

    int leftBorder = 1, rightBorder = n - 1;
    while(leftBorder < n && a[leftBorder] >= a[leftBorder - 1]) {
      leftBorder++;
    }
    if (leftBorder == n) return 0;

    while(rightBorder > 0 && a[rightBorder - 1] <= a[rightBorder]) {
      rightBorder--;
    }
    System.out.println("leftBorder: " + leftBorder + " rightBorder: " + rightBorder);

    int max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
    for (int i = leftBorder; i <= rightBorder; i++) {
      if (a[i] > max) {
        max = a[i];
      }
      if (a[i] < min) {
        min = a[i];
      }
    }
    while(leftBorder > 0 && min < a[leftBorder - 1]) {
      leftBorder--;
      max = Math.max(a[leftBorder], max);
      min = Math.min(a[leftBorder], min);
    }
    System.out.println("max: " + max);
    while(rightBorder + 1 < n && max > a[rightBorder + 1]) {
      rightBorder++;
      max = Math.max(a[rightBorder], max);
      min = Math.min(a[rightBorder], min);
    }
    System.out.println("after: " + "leftBorder: " + leftBorder + " rightBorder: " + rightBorder);
    return rightBorder - leftBorder + 1;
  }
}
