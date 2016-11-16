class MergeSort {
  private static Comparable [] temp; //temporary array

  public static void sort(Comparable [] a) {
    temp = new Comparable[a.length];
    sort(a, 0, a.length - 1);
  }

  private static void sort(Comparable [] a, int lo, int hi) {
    if (lo == hi) return; // array with a single element, no need to sort
    int mid = (lo + hi) / 2;
    sort(a, lo, mid);
    sort(a, mid + 1, hi);
    merge(a, lo, mid, hi);
  }

  /**
   * merges two parts of array a (sub-arrays a[low..mid] and a[mid+1..hi]) in sorted order
   */
  private static void merge(Comparable [] a, int lo, int mid, int hi) {
    //copying values from lo to hi into the temporary array
    for (int k = lo; k <= hi; k++)
      temp[k] = a[k];
    int i = lo, j = mid + 1; //pointers to the beginnings of sub-arrays
    for (int k = lo; k <= hi; k++) {
      if (i > mid) a[k] = temp[j++];                             // left is exhausted, taking from right
      else if (j > hi) a[k] = temp[i++];                         // right is exhausted, taking from left
      else if (temp[i].compareTo(temp[j]) < 0) a[k] = temp[i++]; // left is smaller, taking it
      else a[k] = temp[j++];                                     // right is smaller, taking it
    }
  }
}
