public class LonelyPixelII533 {
  public int findBlackPixel(char[][] picture, int N) {
    int n = picture.length;
    if (n == 0) return 0;
    int m = picture[0].length;

    Map<String, Integer> counts = new HashMap<>();
    int [] rowCounts = new int[n];
    int [] colCounts = new int[m];
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        if (picture[i][j] == 'B') {
          rowCounts[i]++;
          colCounts[j]++;
        }
      }
      if (rowCounts[i] != N) continue;
      String s = new String(picture[i]);
      counts.put(s, counts.getOrDefault(s, 0) + 1);
    }
    int res = 0;
    for (Map.Entry<String, Integer> entry : counts.entrySet()) {
      String row = entry.getKey();
      int dupCount = entry.getValue();
      if (dupCount != N) continue;
      for (int i = 0; i < row.length(); i++) {
        if (row.charAt(i) == 'B' && colCounts[i] == dupCount) {
          res += dupCount;
        }
      }
    }
    return res;
  }
}
