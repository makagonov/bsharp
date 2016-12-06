import java.util.ArrayList;
import java.util.List;

public class Combinations77 {
  public List<List<Integer>> combine(int n, int k) {
    int [] a = new int[k];
    for (int i = 1; i <= k; i++)
      a[i - 1] = i;

    List<List<Integer>> result = new ArrayList<>();
    do {
      result.add(asList(a));
    } while(hasNext(a, n));

    return result;
  }
  
  private static List<Integer> asList(int [] a) {
    List<Integer> result = new ArrayList<>();
    for (int i : a)
      result.add(i);
    return result;
  }
  

  private boolean hasNext(int [] a, int n) {
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
  
  public static void main (String [] args) {
    System.out.println(new Combinations77().combine(4, 2));
  }
}