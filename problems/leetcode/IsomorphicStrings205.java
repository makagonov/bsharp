public class IsomorphicStrings205 {
  public boolean isIsomorphic(String s, String t) {
    if (s == null || t == null || s.length() != t.length())
      return false;
    HashMap<Character, Character> mapping1 = new HashMap<>();
    HashMap<Character, Character> mapping2 = new HashMap<>();
    for (int i = 0; i < s.length(); i++) {
      char cS = s.charAt(i);
      char cT = t.charAt(i);
      mapping1.putIfAbsent(cS, cT);
      mapping2.putIfAbsent(cT, cS);
      if (mapping1.get(cS) != cT || mapping2.get(cT) != cS) {
        return false;
      }
    }
    return true;
  }
}
