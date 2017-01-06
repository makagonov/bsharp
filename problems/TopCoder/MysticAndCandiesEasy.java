import java.util.*;

public class MysticAndCandiesEasy {

	public int minBoxes(int C, int X, int[] high) {
		int n = high.length;
		if (C == X) return n;
		
		Arrays.sort(high);
		int [] sum = new int [n];
		sum[0] = high[0];
		for (int i = 1; i < n; i++) {
			sum[i] = sum[i - 1] + high[i];
		}
		
		for (int i = n - 1; i > 0; i--) {
			int atMostRest = Math.min(sum[i - 1], C);
			int atLeastHave = C - atMostRest;
			if (atLeastHave >= X)
				return n - i; 
		}
		
		return n;
	}

}
