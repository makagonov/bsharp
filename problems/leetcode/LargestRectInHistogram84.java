public class LargestRectInHistogram84 {
  public int largestRectangleArea(int[] heights) {
    int n = heights.length;
    int [] leftToRight = solve(heights, 0, n, 1);
    int [] rightToLeft = solve(heights, n - 1, -1, -1);
    int best = 0;
    for (int i = 0; i < n; i++) {
      best = Math.max(best, (leftToRight[i] + rightToLeft[i] + 1) * heights[i]);
    }
    return best;
  }
  
  private int [] solve(int [] heights, int start, int end, int inc) {
    int [] ret = new int[heights.length];
    int [] stack = new int[heights.length];
    int stackSize = 0;
    for (int i = start; i != end; i += inc) {
      int cur = heights[i];
      while(stackSize > 0 && heights[stack[stackSize - 1]] >= cur) {
        int x = stack[--stackSize];
        ret[i] += (ret[x] + 1);
      }
      stack[stackSize++] = i;
    }
    return ret;
  }
  
  public static void main (String [] ags) {
//    System.out.println(new Solution().largestRectangleArea(new int []{2,1,5,6,2,3}));
//    System.out.println(new Solution().largestRectangleArea(new int []{2,1,1,6,2,3}));
  }
}