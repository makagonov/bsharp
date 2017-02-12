public class ProductofArrayExceptSelf238 {
  public int[] productExceptSelf(int[] nums) {
    int n = nums.length;
    int[] res = new int[n];
    res[0] = 1;
    int p = 1;
    for (int i = 1; i < n; i++) {
      p *= nums[i - 1];
      res[i] = p;
    }
    p = 1;
    for (int i = n - 2; i >= 0; i--) {
      p *= nums[i + 1];
      res[i] *= p;
    }

    return res;
  }
}
