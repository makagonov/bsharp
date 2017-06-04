public class MergeSortedArray88 {
  public void merge(int[] nums1, int m, int[] nums2, int n) {
    int [] res = new int[n + m];
    int p1 = 0, p2 = 0;
    for (int i = 0; i < n + m; i++) {
      if (p1 >= m) res[i] = nums2[p2++];
      else if (p2 >= n) res[i] = nums1[p1++];
      else if (nums1[p1] <= nums2[p2]) res[i] = nums1[p1++];
      else res[i] = nums2[p2++];
    }

    for (int i = 0; i < n + m; i++) {
      nums1[i] = res[i];
    }
  }
}
