import java.util.*;

public class MagicalStringDiv1 {

	public int getLongest(String S) {
	  if (S.length() == 1) return 0;
	  int n = S.length();
	  int [] countG = new int[n];
	  int [] countL = new int[n];
	  for (int i = 0, g = 0, l = 0; i < n; i++) {
	    if (S.charAt(i) == '<') l++;
	    else g++;
	    countL[i] = l;
	    countG[i] = g;
	  }
	  
	  for (int k = n / 2; k > 0; k--)
	    for (int i = 0; i < n; i++)
	      if (countG[i] >= k && countL[n - 1] - countL[i] >= k)
	        return k * 2;
		
	  return 0;
	}

}
