import java.util.*;

public class GroupAnagrams49 {
  final long P = 31;
  final long [] primePowers;
  public GroupAnagrams49() {
    primePowers = new long[50];
    primePowers[0] = 1;
    for (int i = 1; i < primePowers.length; i++)
      primePowers[i] = primePowers[i - 1] * P;
  }
  
  public List<List<String>> groupAnagrams(String[] strs) {
    int [] counts = new int[27];
    Map<Long, List<String>> map = new HashMap<>();
    for (String s : strs) {
      Arrays.fill(counts, 0);
      for (int i = 0; i < s.length(); i++)
        counts[s.charAt(i) -'a' + 1]++;
      int position = 0;
      long hash = 0;
      for (int i = 1; i < counts.length; i++) {
        for (int j = 0; j < counts[i]; j++) {
          hash += primePowers[position++] * i; 
        }
      }
      List<String> hashLst = map.getOrDefault(hash, new LinkedList());
      hashLst.add(s);
      map.put(hash, hashLst);
    }
    return new ArrayList<>(map.values());
  }
  
  public static void main (String [] args) {
    System.out.println(new GroupAnagrams49().groupAnagrams(new String[]{"eat", "tea", "tan", "ate", "nat", "bat"}));
  }
}

