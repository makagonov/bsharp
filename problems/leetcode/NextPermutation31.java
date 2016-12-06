import java.util.Arrays;

public class NextPermutation31 {
  public void nextPermutation(int[] a) {
    int pivot = -1;
    for (int i = a.length - 2; i >= 0; i--) {
      if (a[i] < a[i + 1]) {
        pivot = i;
        break;
      }
    }
    if (pivot < 0) {
      Arrays.sort(a);
      return;
    }
    int rightMostGreater = -1;
    for (int i = a.length - 1; i > pivot; i--) {
      if (a[i] > a[pivot]) {
        rightMostGreater = i;
        break;
      }
    }
    swap(a, rightMostGreater, pivot);
    for (int i = pivot + 1, j = a.length - 1; i < j; i++, j--) {
      swap(a, i, j);
    }
  }

  private void swap(int [] a, int pos1, int pos2) {
    int tmp = a[pos1];
    a[pos1] = a[pos2];
    a[pos2] = tmp;
  }
}
