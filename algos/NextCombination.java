import java.util.Arrays;

public class NextCombination {
  public static boolean hasNext(int [] a, int n) {
    int pivot = -1;
    
    for (int i = a.length - 1, j = n; i >= 0; i--, j--) {
      if (a[i] < j) {
        pivot = i;
        break;
      }
    }
    if (pivot < 0) return false;
    a[pivot]++;
    for (int i = pivot + 1; i < a.length; i++)
      a[i] = a[i - 1] + 1;
    return true;
  }
  
  public static void main(String [] args) {
    int [] a = new int []{1, 2};
    int n = 4;
    do {
      System.out.println(Arrays.toString(a));
    } while (NextCombination.hasNext(a,  n));
  }
}
