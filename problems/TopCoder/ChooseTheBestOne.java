import java.util.*;

public class ChooseTheBestOne {

	public int countNumber(int N) {
	  if (N == 1) return 1;
	  List<Integer> l = new ArrayList<>();
	  for (int i = 2; i <= N; i++)
	    l.add(i);
	  int curPos = 0;
	  for (long t = 2; l.size() > 1; t++, curPos %= l.size()) {
	    long removePos = (t * t * t - 1) % l.size() + curPos;
	    if (removePos >= l.size()) removePos %= l.size();
	    l.remove((int)removePos);
	    curPos = (int)removePos;
	  }
	  return l.get(0);
	}
}
