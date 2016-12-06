import java.util.Arrays;

public class NextPermutation {
  public static boolean hasNext(int [] a) {
    // finding the rightmost position "pivot",
    // such that a[pivot] < a[pivot + 1]
    // pivot + 1 is actually the left border of the longest decreasing suffix
    int pivot = -1;
    for (int i = a.length - 2; i >= 0; i--) {
      if (a[i] < a[i + 1]) {
        pivot = i;
        break;
      }
    }
    // pivot not found - no more permutations 
    if (pivot < 0) return false;
    // next, finding the rightmost position,
    // such that a[rightMostGreater] > a[pivot]
    int rightMostGreater = -1;
    for (int i = a.length - 1; i > pivot; i--) {
      if (a[i] > a[pivot]) {
        rightMostGreater = i;
        break;
      }
    }
    // swapping pivot and rightMostGreater 
    swap(a, rightMostGreater, pivot);
    // reversing the elements at positions [pivot + 1, length - 1]
    // which is basically sorting them, as they are in descending order
    for (int i = pivot + 1, j = a.length - 1; i < j; i++, j--) {
      swap(a, i, j);
    }
    
    return true;
  }
  
  private static void swap(int [] a, int pos1, int pos2) {
    int tmp = a[pos1];
    a[pos1] = a[pos2];
    a[pos2] = tmp;
  }
  
  public static void main (String [] args) {
    int [] a = new int []{1, 2, 3, 4};
    int count = 0;
    do {
      System.out.println(++count + " " + Arrays.toString(a));
    } while(NextPermutation.hasNext(a));
    
  }

}
