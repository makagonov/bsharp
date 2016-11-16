import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class QuickSort {
  private static Random rnd = new Random();
  public static void sort(int [] a) {
    quickSort(a, 0, a.length - 1);
  }

  private static void quickSort(int [] a , int lo, int hi) {
    if (lo >= hi) return;
    int pivot = partition(a, lo, hi);
    quickSort(a, lo, pivot - 1); // no need to include element at pivot position
    quickSort(a, pivot + 1, hi); // as this element is on its place in the sorted array
  }

  /**
   * finding pivot that split array into two sub-arrays, s.t:
   * all elements at positions < pivot are less or equal to element at pivot
   * all elements at positions > povot are strictly greater than pivot
   */
  private static int partition(int [] a, int lo, int hi) {
    int pivot = lo;
    //randomizing pivot
    int randomPos = lo + rnd.nextInt(hi - lo + 1);
    swap(a, randomPos, hi);

    int x = a[hi];
    for (int j = lo; j < hi; j++) {
      if (a[j] <= x) {
        swap(a, pivot++, j);
      }
    }
    swap(a, pivot, hi);
    return pivot;
  }

  private static void swap(int [] a, int pos1, int pos2) {
    int tmp = a[pos1];
    a[pos1] = a[pos2];
    a[pos2] = tmp;
  }

  public static void main (String [] args) {
    Scanner in = new Scanner(System.in);
    int n = in.nextInt();
    int [] a = new int [n];
    for (int i = 0; i < n; i++)
      a[i] = in.nextInt();
    QuickSort.sort(a);
    System.out.println(Arrays.toString(a));
  }
}
