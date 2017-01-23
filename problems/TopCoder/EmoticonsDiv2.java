import java.util.*;

public class EmoticonsDiv2 {
  final int oo = Integer.MAX_VALUE / 2;
	public int printSmiles(int smiles) {
	  int [][] d = new int[smiles + 1][smiles + 1];
	  for (int i = 0; i <= smiles; i++)
	    Arrays.fill(d[i], oo);
	  d[1][0] = 0;
	  PriorityQueue<State> q = new PriorityQueue<>((a, b) -> a.time - b.time);
	  q.add(new State(1, 0, 0));
	  while(!q.isEmpty()) {
	    State s = q.poll();
	    
	    if (s.n + s.buffer <= smiles && d[s.n + s.buffer][s.buffer] > s.time + 1) {
	      d[s.n + s.buffer][s.buffer] = s.time + 1;
	      q.add(new State(s.n + s.buffer, s.buffer, s.time + 1));
	    }
	    if (d[s.n][s.n] > s.time + 1) {
	      d[s.n][s.n] = s.time + 1;
	      q.add(new State(s.n, s.n, s.time + 1));
	    }
	  }
	  
	  int best = oo;
	  for (int i = 0; i <= smiles; i++)
	    best = Math.min(best, d[smiles][i]);
	  return best;
	}
	
	class State {
	  int n;
	  int buffer;
	  int time;
	  public State(int n, int b, int t) {
	    this.n = n;
	    this.buffer = b;
	    this.time = t;
	  }
	}

}
