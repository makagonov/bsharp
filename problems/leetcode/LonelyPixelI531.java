public class LonelyPixelI531 {
  public int findLonelyPixel(char[][] picture) {
    int n = picture.length;
    if (n == 0) return 0;
    int m = picture[0].length;
    int [] rowCounts = new int[n];
    int [] colCounts = new int[m];
    for (int i = 0; i < n; i++)
      for (int j = 0; j < m; j++)
        if (picture[i][j] == 'B') {
          rowCounts[i]++;
          colCounts[j]++;
        }
    int res = 0;
    for (int i = 0; i < n; i++)
      for (int j = 0; j < m; j++)
        if (picture[i][j] == 'B' && rowCounts[i] == 1 && colCounts[j] == 1)
          res++;
    return res;
  }
}
