public class LongLongTripDiv2 {

  public String isAble(long D, int T, int B) {
    if (D == T || D % B == 0 && D / B == T) return getResult(true);
    if (T > D) return getResult(false);
    // obtaining a system of linear equations:
    // {a + b*B = D, a + b = T} => b = (T - D) / (1 - B)
    long numLongSteps = (T - D) / (1 - B);
    long numShortSteps = T - numLongSteps;
    //doing validation of obtained a and b
    boolean isGood = numLongSteps >= 0 && numShortSteps >= 0 && 
        numShortSteps + numLongSteps * B == D; 
    return getResult(isGood);
  }
  
  String getResult(boolean result) {
    return result ? "Possible" : "Impossible";
  }

}
