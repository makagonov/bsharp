public class ValidParentheses20 {
  public boolean isValid(String s) {
    Stack<Character> stack = new Stack<>();
    for (int i = 0; i < s.length(); i++) {
      char c = s.charAt(i);
      if (c == '(' || c == '[' || c == '{') {
        stack.push(c);
      } else {
        if (stack.size() == 0) return false;
        char top = stack.pop();
        if (top != getOpenParan(c))
          return false;
      }
    }
    return stack.isEmpty();
  }

  char getOpenParan(char closeParan) {
    switch(closeParan) {
      case ')': return '(';
      case ']': return '[';
      default: return '{';
    }
  }
}
