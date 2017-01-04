import java.util.*;

public class EllysNumberGuessing {
  final int MINIMUM_VALID_NUMBER = 1;
  final int MAXIMUM_VALID_NUMBER = 1000000000;
  
  public int getNumber(int[] guesses, int[] answers) {
    HashSet<Integer> numbers = new HashSet<>();
    tryAddNumbers(numbers, guesses, answers, 0);
    
    for (int i = 1; i < guesses.length; i++) {
      HashSet<Integer> newNumbers = new HashSet<>();
      tryAddNumbers(newNumbers, guesses, answers, i);
      numbers.retainAll(newNumbers);
    }
    if (numbers.size() == 0)
      return -2;
    
    if (numbers.size() == 1)
      return (int)numbers.toArray()[0];
    return -1;
  }
  
  private boolean isValidNumber(int a) {
    return a >= MINIMUM_VALID_NUMBER && a <= MAXIMUM_VALID_NUMBER;
  }
  
  void tryAddNumbers(HashSet<Integer> set, int [] guesses, int [] answers, int pos) {
    int a = guesses[pos] + answers[pos];
    int b = guesses[pos] - answers[pos];
    if (isValidNumber(a))
      set.add(a);
    if (isValidNumber(b))
      set.add(b);
  }  
}
