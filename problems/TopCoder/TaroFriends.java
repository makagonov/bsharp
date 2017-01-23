import java.util.*;

public class TaroFriends {

	public int getNumber(int[] coordinates, int X) {
	  int n = coordinates.length;
	  if (n == 1) return 0;
	  
	  Cat [] cats = new Cat[n * 2];
	  for (int i = 0; i < n; i++) {
	    cats[i * 2] = new Cat(coordinates[i] + X, i);
	    cats[i * 2 + 1] = new Cat(coordinates[i] - X, i);
	  }
	  
	  int best = Integer.MAX_VALUE;
	  for (Cat left : cats) {
	    for (Cat right : cats) {
	      int candidateDistance =  right.pos - left.pos;
	      if (left.idx == right.idx || right.pos < left.pos || candidateDistance > best) continue;
	      boolean [] used = new boolean[n];
	      used[left.idx] = used[right.idx] = true;
	      for (Cat middle : cats) {
	        if (!used[middle.idx] && middle.pos >= left.pos && middle.pos <= right.pos) {
	          used[middle.idx] = true;
	        }
	      }
	      boolean good = true;
	      for (int i = 0; i < n; i++) {
	        if (!used[i])
	          good = false;
	      }
	      if (good) best = candidateDistance;
	    }
	  }
		return best;
	}
	
	class Cat {
	  int pos;
	  int idx;
	  public Cat(int p, int i) {
	    pos = p;
	    idx = i;
	  }
	}
}
