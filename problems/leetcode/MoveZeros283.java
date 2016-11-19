
public class MoveZeros283 {
  public void moveZeroesTrivial(int[] nums) { //N^2 solution
    int n = nums.length;
    for (int i = 0; i < n; i++) {
      if (nums[i] != 0) continue;
      for (int j = i + 1; j < n; j++)
        if (nums[j] != 0) {                                                
          swap(nums, i, j);
          break;
        }
    }
  }

  public void moveZeros(int [] nums) {
    int n = nums.length;
    int p = 0; //position where the next non-zero element should be put
    
    for (int i = 0; i < n; i++) {
      if (nums[i] == 0) continue;
      nums[p++] = nums[i];
    }
    
    //not filling the rest of the elements with zeros
    for (int i = p; i < n; i++)
      nums[i] = 0;
  }
  

  void swap(int [] a, int pos1, int pos2) {
    int tmp = a[pos1];
    a[pos1] = a[pos2];
    a[pos2] = tmp;
  }


}
