import java.util.*;

public class AlienAndHamburgers {

  public int getNumber(int[] type, int[] taste) {
    int n = type.length;
    boolean allNegative = true;
    for (int i : taste)
      if (i > 0)
        allNegative = false;
    if (allNegative) return 0;
    
    HashSet<Integer> baseTypes = new HashSet<>();
    int baseTasteSum = 0;
    for (int i = 0; i < n; i++) {
      if (taste[i] >= 0) {
        baseTypes.add(type[i]);
        baseTasteSum += taste[i];
      }
    }
    
    Map<Integer, Integer> maxByType = new HashMap<>();
    for (int i = 0; i < n; i++) {
      if (taste[i] >= 0) continue;
      if (baseTypes.contains(type[i])) continue;
      if (!maxByType.containsKey(type[i]) || maxByType.get(type[i]) < taste[i]) {
        maxByType.put(type[i], taste[i]);
      }
    }
    
    ArrayList<Map.Entry<Integer, Integer>> negatives = new ArrayList<>(maxByType.entrySet());
    negatives.sort((a, b) -> b.getValue() - a.getValue());
    
    int currentBest = baseTypes.size() * baseTasteSum;
    for (Map.Entry<Integer, Integer> entry : negatives) {
      int newType = entry.getKey();
      int newTaste = entry.getValue();
      if ((baseTypes.size() + 1) * (newTaste + baseTasteSum) > currentBest) {
        baseTypes.add(newType);
        baseTasteSum += newTaste;
        currentBest = baseTypes.size() * baseTasteSum;
      }
    }
    
    return currentBest;
  }
}
