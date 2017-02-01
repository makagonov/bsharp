import java.util.*;

public class MazeConstruct {
  final int MAX_DIM = 50;

  public String[] construct(int k) {
    k++;
    if (k <= 99) {
      return constructSmall(k);
    }
    for (int width = 2; width <= MAX_DIM; width++) {
      for (int height = 5; height <= MAX_DIM; height++) {
        int curPathLen = width + height - 1;
        List<Integer> humps = new ArrayList<>();
        int currentHumpWidth = 0;
        while(curPathLen < k) {
          currentHumpWidth++;
          curPathLen += 2;
          if (currentHumpWidth == width - 1) {
            humps.add(currentHumpWidth);
            currentHumpWidth = 0;
          }
        }
        if (currentHumpWidth > 0) {
          humps.add(currentHumpWidth);
        }
        if (curPathLen == k) {
          char [][] tryArr = tryConstructRectangle(width, height, k, humps);
          if (tryArr != null) {
            return charArrayToStringArray(tryArr);
          }
        }
      }
    }

    return new String []{};
  }

  char [][] tryConstructRectangle(int width, int height, int k, List<Integer> humps) {
    char [][] arr = new char[height][width];
    for (int i = 0; i < arr.length; i++)
      Arrays.fill(arr[i], '#');

    for (int i = 0; i < width; i++)
      arr[0][i] = '.';

    for (int i = 0; i < height; i++)
      arr[i][width - 1] = '.';

    for (int i = 0, j = 3; i < humps.size() && j < height; i++, j += 4) {
      arr[j][width - 1] = '#';
    }

    for (int i = 0, j = 2; i < humps.size() && j < height; i++, j += 4) {
      for (int f = 0; f < humps.get(i); f++) {
        arr[j][width - f - 2] = '.';
        if (j + 2 < height) {
          arr[j + 2][width - f - 2] = '.';
        }
      }
    }

    for (int i = 0, j = 3; i < humps.size() && j < height; i++, j += 4) {
      arr[j][width - humps.get(i) - 1] = '.';
    }

    int countDots = 0;
    for (char [] a : arr)
      for (char c : a)
        if (c == '.')
          countDots++;


    if (countDots == k)
      return arr;

    return null;
  }

  String[] constructSmall(int k) {
    if (k <= MAX_DIM) {
      char [][] arr = new char[1][k];
      Arrays.fill(arr[0], '.');
      return charArrayToStringArray(arr);
    }
    char [][] arr = new char[k - MAX_DIM + 1][MAX_DIM];
    for (int i = 0; i < arr.length; i++)
      Arrays.fill(arr[i], '#');

    for (int i = 0; i < arr[0].length; i++)
      arr[0][i] = '.';

    for (int i = 0; i < arr.length; i++)
      arr[i][arr[0].length - 1] = '.';

    return charArrayToStringArray(arr);
  }

  String[] charArrayToStringArray(char [][] arr) {
    String [] result = new String[arr.length];
    for(int i = 0; i < result.length; i++)
      result[i] = new String(arr[i]);
    return result;
  }
}
