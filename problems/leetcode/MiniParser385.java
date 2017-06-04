public class MiniParser385 {

  class EvalResult {
    List res;
    int finalPos;
    public EvalResult(List r, int f) {
      res = r;
      finalPos = f;
    }
  }

  EvalResult eval(List cur, String s, int pos) {
    int finalPos = pos;
    StringBuilder curNumber = new StringBuilder();
    EvalResult result = null;
    while(finalPos < s.length()) {
      char c = s.charAt(finalPos);
      if (c >= '0' && c <= '9' || c == '-') {
        curNumber.append(c);
        finalPos++;
      } else if (c == ',' || c == ']') {
        if (curNumber.length() > 0) {
          cur.add(Integer.parseInt(curNumber.toString()));
          curNumber.setLength(0);
        }

        if (c == ',') {
          finalPos++;
        } else {
          result = new EvalResult(cur, finalPos);
          break;
        }
      } else if (c == '[') {
        EvalResult childRes = eval(new ArrayList(), s, finalPos + 1);
        cur.add(childRes.res);
        finalPos = childRes.finalPos + 1;
      }
    }
    return result;
  }

  NestedInteger toNestedInteger(Object o) {
    if (o instanceof Integer) {
      return new NestedInteger((Integer)o);
    }
    NestedInteger ni = new NestedInteger();
    List lst = (List)o;
    for (Object i : lst) {
      ni.add(toNestedInteger(i));
    }
    return ni;
  }

  public NestedInteger deserialize(String s) {
    List evalRes = eval(new ArrayList(), "[" + s + "]", 1).res;
    if (evalRes.size() == 1) {
      return toNestedInteger(evalRes.get(0));
    }
    return toNestedInteger(evalRes);
  }
}
