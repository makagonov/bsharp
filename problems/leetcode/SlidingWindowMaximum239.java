public class SlidingWindowMaximum239 {
  public int[] maxSlidingWindow(int[] nums, int k) {
    int n = nums.length;
    if (n == 0) return new int[0];
    if (k == 1) return nums;

    MaxStack s1 = new MaxStack();
    MaxStack s2 = new MaxStack();
    for (int i = 0; i < k; i++) {
      s1.push(nums[i]);
    }

    int [] res = new int[n - k + 1];

    for (int i = k; i <= n; i++) {
      if (s2.isEmpty()) {
        while (!s1.isEmpty()) {
          s2.push(s1.pop());
        }
      }
      int max = s2.getMax();
      if (!s1.isEmpty()) max = Math.max(max, s1.getMax());
      res[i - k] = max;
      s2.pop();
      if (i != n) {
        s1.push(nums[i]);
      }
    }

    return res;
  }

  class MaxStack {

    class Item {
      int val;
      int max;
      Item(int v, int m) {
        this.val = v;
        this.max = m;
      }
    }

    Stack<Item> stack;

    public MaxStack() {
      stack = new Stack<>();
    }

    public void push(int x) {
      int max = stack.isEmpty() ? Integer.MIN_VALUE : getMax();
      stack.push(new Item(x, Math.max(x, max)));
    }

    public int pop() {
      return stack.pop().val;
    }

    public int top() {
      return stack.peek().val;
    }

    public int getMax() {
      return stack.peek().max;
    }

    public boolean isEmpty() {
      return stack.isEmpty();
    }
  }
}
