import java.util.Arrays;

public class RadixSortStrings {
  private static final int ALPHABET_SIZE = 256;
  public static void sort(String [] a) {
    int maxLength = 1;
    for (String s : a)
      if (s.length() > maxLength)
        maxLength = s.length();
    for (int pos = maxLength - 1; pos >= 0; pos--) {
      sortByPos(a, pos);
    }
  }
  
  private static char getCharAt(String s, int pos) {
    return pos >= s.length() ? 0 : s.charAt(pos);
  }
  
  private static void sortByPos(String [] a, int pos) {
    int n = a.length;
    int [] counts = new int[ALPHABET_SIZE];
    for (String s : a) {
      char c = getCharAt(s, pos);
      counts[c]++;
    }
    
    for (int i = 1; i < ALPHABET_SIZE; i++) {
      counts[i] += counts[i - 1];
    }
    String [] sorted = new String[n + 1];
    for (int i = a.length - 1; i >= 0; i--) {
      char c = getCharAt(a[i], pos);
      sorted[counts[c]] = a[i];
      counts[c]--;
    }
    for (int i = 1; i <= n; i++)
      a[i - 1] = sorted[i];
  }
  
  public static void main (String [] args) {
    String [] b = new String[]{"YOYO", "YO", "COW", "DOG", "SEA", "RUG", "ROW", "MOB", "BOX", "TAB", "BAR", "EAR", "TAR", "DIG", "BIG", "TEA", "NOW", "FOX"};
    String [] bClone = b.clone();
    RadixSortStrings.sort(b);
    System.out.println(Arrays.toString(b));
    Arrays.sort(bClone);
    System.out.println(Arrays.toString(bClone));
  }
}
