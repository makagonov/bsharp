import java.util.*;

public class Permutations46 {
  private static boolean hasNext(int [] a) {
    int pivot = -1;
    for (int i = a.length - 2; i >= 0; i--) {
      if (a[i] < a[i + 1]) {
        pivot = i;
        break;
      }
    }
    if (pivot < 0) return false;
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
    return true;
  }
  
  private static void swap(int [] a, int pos1, int pos2) {
    int tmp = a[pos1];
    a[pos1] = a[pos2];
    a[pos2] = tmp;
  }
  
  private static List<Integer> asList(int [] a) {
    List<Integer> result = new ArrayList<>();
    for (int i : a)
      result.add(i);
    return result;
  }
  
  public List<List<Integer>> permute(int[] nums) {
    Arrays.sort(nums);
    List<List<Integer>> result = new ArrayList<>();
    do {
      result.add(asList(nums));
    } while(hasNext(nums));

    return result;
  }
  
  
  public static void main (String [] args) {
    System.out.println(new Permutations46().permute(new int []{1, 2, 3}));
  }
  
}
