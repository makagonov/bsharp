public class 3Sum15 {
  public List<List<Integer>> threeSum(int[] arr) {
    List<List<Integer>> res = new LinkedList<>();
    int n = arr.length;
    if (n == 0) return res;
    Arrays.sort(arr);
    for (int a = 0; a < n - 2; a++) {
      if (a > 0 && arr[a] == arr[a - 1]) continue;
      int left = arr[a];
      int p1 = a + 1;
      int p2 = n - 1;
      while(p1 < p2) {
        int middle = arr[p1], right = arr[p2];
        int sum = left + middle + right;
        boolean moveP1 = false, moveP2 = false;
        if (sum == 0) {
          res.add(Arrays.asList(left, middle, right));
          moveP1 = moveP2 = true;
        } else if (sum < 0) {
          moveP1 = true;
        } else {
          moveP2 = true;
        }
        if (moveP1) {
          while(p1 < n && arr[p1] == middle)
            p1++;
        }
        if (moveP2) {
          while(p2 >= 0 && arr[p2] == right)
            p2--;
        }
      }
    }
    return res;
  }
}
