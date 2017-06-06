public class RemoveDuplicates26 {

  void swap(int [] a, int p1, int p2) {
    int tmp = a[p1];
    a[p1] = a[p2];
    a[p2] = tmp;
  }
  public int removeDuplicates(int[] a) {
    int size = a.length;
    for (int i = 1; i < size; ) {
      int prev = a[i - 1];
      if (a[i] == prev) {
        for (int j = i; j + 1 < size; j++) {
          swap(a, j, j + 1);
        }
        size--;
      } else {
        i++;
      }
    }
    System.out.println(size);
    return size;
  }
}
