import java.util.Arrays;
import java.util.Scanner;

public class KMP {
  
  static int [] prefixFunctionTrivial(String s) {
    int n = s.length();
    int [] pi = new int[n];
    for (int i = 1; i < n; i++) {
      for (int k = 1; k < i; k++) {
        if (s.substring(0, k).equals(s.substring(i - k + 1, i + 1))) {
          pi[i] = k;
        }
      }
    }
    return pi;
  }
  
  static int [] prefixFunction(String s) {
    int n = s.length();
    int [] pi = new int[n];
    for (int i = 1; i < n; i++) {
      int j = pi[i - 1];
      while (j > 0 && s.charAt(i) != s.charAt(j)) {
        j = pi[j - 1];
      }
      if (s.charAt(i) == s.charAt(j))
        j++;
      pi[i] = j;
    }
    return pi;
  }
  
  
  public static void main (String [] args) {
    Scanner in = new Scanner(System.in);
    String text = in.nextLine();
    String pattern = in.nextLine();
    int [] pi = prefixFunction(pattern + "#" + text);
    for (int i = pattern.length() + 1; i < pi.length; i++)
      if (pi[i] == pattern.length()) {
        System.out.print(i - 2 * pattern.length() + " ");
      }
    System.out.println();
    
  }

}
