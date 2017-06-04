public class BasicCalculatorII227 {
  public int calculate(String s) {
    return evalExpression(removeSpaces(s));
  }

  String removeSpaces(String s) {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < s.length(); i++) {
      char c = s.charAt(i);
      if (c != ' ') {
        sb.append(c);
      }
    }
    return sb.toString();
  }

  private int getOperatorPriority(char c) {
    if (c == '-' || c == '+') return 0;
    return 1;
  }

  int [] extractNumbers(String s) {
    String [] operands = s.split("[\\+\\-\\*\\/]");
    int [] ret = new int[operands.length];
    for (int i = 0; i < ret.length; i++) {
      ret[i] = Integer.parseInt(operands[i]);
    }
    return ret;
  }

  String extractOperators(String s) {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < s.length(); i++) {
      char c = s.charAt(i);
      if (c == '+' || c == '-' || c == '/' || c == '*') {
        sb.append(c);
      }
    }
    return sb.toString();
  }

  private int doOperation(int a, int b, char op) {
    if (op == '+') return a + b;
    if (op == '-') return a - b;
    if (op == '*') return a * b;
    return a / b;
  }

  private int evalExpression(String s) {
    int [] numbers = extractNumbers(s);
    String operators = extractOperators(s);
    Stack<Character> opStack = new Stack<>();
    Stack<Integer> numStack = new Stack<>();
    numStack.push(numbers[0]);
    for (int i = 1; i < numbers.length; i++) {
      char op = operators.charAt(i - 1);
      int opPriority = getOperatorPriority(op);
      while(!opStack.isEmpty() && opPriority <= getOperatorPriority(opStack.peek())) {
        int num2 = numStack.pop();
        int num1 = numStack.pop();
        numStack.push(doOperation(num1, num2, opStack.pop()));
      }
      opStack.push(op);
      numStack.push(numbers[i]);
    }

    while(!opStack.isEmpty()) {
      int num2 = numStack.pop();
      int num1 = numStack.pop();
      numStack.push(doOperation(num1, num2, opStack.pop()));
    }

    return numStack.pop();
  }
}
