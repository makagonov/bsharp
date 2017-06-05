public class MinStack155 {

  static class Item {
    int val;
    int min;
    Item(int v, int m) {
      this.val = v;
      this.min = m;
    }
  }

  Stack<Item> stack;

  public MinStack() {
    stack = new Stack<>();
  }

  public void push(int x) {
    int min = stack.isEmpty() ? Integer.MAX_VALUE : getMin();
    stack.push(new Item(x, Math.min(x, min)));
  }

  public void pop() {
    stack.pop();
  }

  public int top() {
    return stack.peek().val;
  }

  public int getMin() {
    return stack.peek().min;
  }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */
