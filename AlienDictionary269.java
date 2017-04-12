public class AlienDictionary269 {
  final int ALPHABET_SIZE = 26;

  void buildDependencies(String [] w, boolean [][] g) {
    if (w.length == 0) return;
    int [] firstCharCounts = new int[ALPHABET_SIZE];
    for (String s : w) {
      if (s.length() == 1) continue;
      int c = s.charAt(0) - 'a';
      firstCharCounts[c]++;
    }

    for (int i = 1; i < w.length; i++) {
      int c1 = w[i].charAt(0) - 'a';
      for (int j = 0; j < i; j++) {
        int c2 = w[j].charAt(0) - 'a';
        if (c1 == c2) continue;
        g[c1][c2] = true;
      }
    }
    for (int i = 0; i < ALPHABET_SIZE; i++) {
      if (firstCharCounts[i] <= 1) continue;
      int count = firstCharCounts[i];
      char c = (char)(i + 'a');
      String [] newW = new String[count];
      int idx = 0;
      for (String s : w) {
        if (s.length() == 1) continue;
        if (s.charAt(0) == c) {
          newW[idx++] = s.substring(1);
        }
      }
      buildDependencies(newW, g);
    }
  }

  void topSort(int v, StringBuilder sb, boolean [][] g, boolean [] used) {
    used[v] = true;
    for (int i = 0; i < used.length; i++) {
      if (g[v][i] && !used[i])
        topSort(i, sb, g, used);
    }
    sb.append((char)(v + 'a'));
  }

  public String alienOrder(String[] words) {
    Set<Character> activeChars = new HashSet<>();
    for (String s : words) {
      for (char c : s.toCharArray()) {
        activeChars.add(c);
      }
    }
    int n = ALPHABET_SIZE;
    boolean [][] g = new boolean[n][n];
    buildDependencies(words, g);
    for (int i = 0; i < n; i++)
      for (int k = 0; k < n; k++)
        for (int j = 0; j < n; j++)
          if (g[i][k] && g[k][j])
            g[i][j] = true;
    int [] depCounts = new int[n];
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        if (g[i][j]) {
          if (g[j][i])
            return "";
          depCounts[j]++;
        }
      }
    }

    StringBuilder res = new StringBuilder();
    boolean [] used = new boolean[n];
    for (int i = 0; i < n; i++) {
      if (depCounts[i] == 0) {
        topSort(i, res, g, used);
      }
    }

    StringBuilder finalRes = new StringBuilder();
    for (int i = 0; i < res.length(); i++) {
      char c = res.charAt(i);
      if (activeChars.contains(c)) {
        finalRes.append(c);
      }
    }
    return finalRes.toString();
  }
}
