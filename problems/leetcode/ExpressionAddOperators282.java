import java.util.*;

class ExpressionAddOperators {
  public static void main(String[] args) {
    System.out.println(new ExpressionAddOperators().eval(new StringBuilder("2+3*8-4*5*6*9-12+10-2*3*4+1000*2-100")));
    System.out.println(new ExpressionAddOperators().addOperators("123", 6));
    System.out.println(new ExpressionAddOperators().addOperators("232", 8));
    System.out.println(new ExpressionAddOperators().addOperators("105", 5));
    System.out.println(new ExpressionAddOperators().addOperators("00", 0));
    System.out.println(new ExpressionAddOperators().addOperators("3456237490", 9191));
    System.out.println(new ExpressionAddOperators().addOperators("", 5));
  }

  int getPriority(char c) {
    switch (c) {
      case '*': return 1;
      case '#': return -1;
      default: return 0;
    }
  }

  long doOperation(long a, long b, char c) {
    switch(c) {
      case '*': return a * b;
      case '-': return a - b;
      default: return a + b;
    }
  }

  boolean isDigit(char c) {
    return c >= '0' && c <= '9';
  }

  long eval(StringBuilder s) {
    Stack<Long> numbers = new Stack<>();
    Stack<Character> operators = new Stack<>();
    StringBuilder curNumber = new StringBuilder();
    s.append('#');
    for (int i = 0; i < s.length(); i++) {
      char c = s.charAt(i);
      if (isDigit(c)) {
        curNumber.append(c);
      } else {
        numbers.add(Long.parseLong(curNumber.toString()));
        curNumber.setLength(0);
        int p = getPriority(c);
        while(!operators.isEmpty() && p <= getPriority(operators.peek())) {
          long right = numbers.pop();
          long left = numbers.pop();
          numbers.add(doOperation(left, right, operators.pop()));
        }
        if (c != '#') {
          operators.add(c);
        }
      }
    }
    s.setLength(s.length() - 1);
    return numbers.pop();
  }

  final char [] OPERATORS = new char[]{'+', '-', '*'};

  void rec(StringBuilder expression, String num, int index, int target, List<String> acc) {
    char nextChar = num.charAt(index);
    if (isDigit(nextChar) && expression.length() > 0 && expression.charAt(expression.length() - 1) == '0') {
      if (expression.length() == 1 || !isDigit(expression.charAt(expression.length() - 2))) {
        return;
      }
    }
    expression.append(nextChar);
    if (index == num.length() - 1) {
      long val = eval(expression);
      if (val == target) {
        acc.add(expression.toString());
      }
    } else {
      rec(expression, num, index + 1, target, acc);
      for (char c : OPERATORS) {
        expression.append(c);
        rec(expression, num, index + 1, target, acc);
        expression.setLength(expression.length() - 1);
      }
    }
    expression.setLength(expression.length() - 1);
  }

  public List<String> addOperators(String num, int target) {
    List<String> result = new LinkedList<>();
    if (num.length() == 0) return result;
    rec(new StringBuilder(), num, 0, target, result);
    return result;
  }

}

