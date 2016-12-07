import java.util.*;

public class ConvertibleStrings {

  public int leastRemovals(String A, String B) {
    char [] a = A.toCharArray();
    char [] b = B.toCharArray();
    int n = a.length;
    if (n == 1) return 0;
    
    char [] alphabet = new char[9];
    for (char c = 'A'; c <= 'I'; c++)
      alphabet[c - 'A'] = c;
    int best = n;
    do {
      int numWrong = 0;
      for (int i = 0; i < n; i++) {
        char c = a[i];
        char mappedC = alphabet[c - 'A'];
        if (b[i] != mappedC)
          numWrong++;
      }
      if (numWrong < best)
        best = numWrong;
    } while(nextPermutation(alphabet));
    
    return best;
  }
  
  boolean nextPermutation(char [] a) {
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
    swap(a, pivot, rightMostGreater);
    for (int i = pivot + 1, j = a.length - 1; i < j; i++, j--)
      swap(a, i, j);
    return true;
  }
  
  private void swap(char [] a, int pos1, int pos2) {
    char tmp = a[pos1];
    a[pos1] = a[pos2];
    a[pos2] = tmp;
  }

}
