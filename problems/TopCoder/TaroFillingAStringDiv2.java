public class TaroFillingAStringDiv2 {

  public int getNumber(String S) {
    int leftBound = 0, rightBound = S.length() - 1;
    while (leftBound < S.length() && S.charAt(leftBound) == '?') 
      leftBound++;
    while (rightBound >= 0 && S.charAt(rightBound) == '?') 
      rightBound--;
    if (leftBound == S.length()) // given string consists only of '?'
      return 0;
    
    char [] arr = new char[rightBound - leftBound + 1];
    for (int i = leftBound; i <= rightBound; i++)
      arr[i - leftBound] = S.charAt(i);
    
    // compute current ugliness score
    int currentScore = 0;
    boolean hasQuestions = false;
    for (int i = 1; i < arr.length; i++) {
      if (arr[i] != '?' ) {
        if (arr[i] == arr[i - 1])
          currentScore++;
      } else {
        hasQuestions = true;
      }
    }
    
    if (!hasQuestions) // no '?' chars 
      return currentScore;
    
    int leftCharIndex = 0;
    for (int i = 1; i < arr.length - 1; i++) {
      if (arr[i] == '?' && arr[i - 1] != '?') {
        leftCharIndex = i - 1;
      }
      if (arr[i] == '?' && arr[i + 1] != '?') {
        int rightCharIndex = i + 1;
        int numConsequtiveQuestions = rightCharIndex - leftCharIndex + 1;
        if (arr[leftCharIndex] == arr[rightCharIndex] && numConsequtiveQuestions % 2 == 0 || 
            arr[leftCharIndex] != arr[rightCharIndex] && numConsequtiveQuestions % 2 == 1)
          currentScore++;
      }
    }
    
    return currentScore;
  }
  
  

}
