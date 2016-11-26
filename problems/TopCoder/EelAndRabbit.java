import java.util.*;

public class EelAndRabbit {

  public int getmax(int[] l, int[] head) {
    int best = 0;
    int n = l.length;
    //obtaining coordinates of heads and tails
    int [] tail = new int [n];
    for (int i = 0; i < n; i++) {
      head[i] = -head[i];
      tail[i] = head[i] - l[i];
    }
    //compressing coordinates to be of order of N
    Set<Integer> coordSet = new TreeSet<>();
    for (int i : head) coordSet.add(i);
    for (int i : tail) coordSet.add(i);
    List<Integer> coordList = new ArrayList<>(coordSet);
    for (int i = 0; i < n; i++) {
      tail[i] = coordList.indexOf(tail[i]);
      head[i] = coordList.indexOf(head[i]);
    }
    //pre-calculating the eels that will be caught at a time
    //for every coordinate (basically for every unique segment ends)
    int numCoordinates = coordList.size();
    Set<Integer> [] catchAtTimes = new HashSet[numCoordinates];
    for (int time = 0; time < numCoordinates; time++) {
      catchAtTimes[time] = catchAtTime(tail, head, time);
    }
    //finding two times that maximize the total number of eels caught 
    for (int time1 = 0; time1 < numCoordinates; time1++) {
      Set<Integer> time1Fish = catchAtTimes[time1];
      for (int time2 = time1 + 1; time2 < numCoordinates; time2++) {
        Set<Integer> time2Fish = new HashSet<>(catchAtTimes[time2]);
        time2Fish.addAll(time1Fish);
        if (time2Fish.size() > best) {
          best = time2Fish.size();
        }
      }
    }

    return best;
  }

  Set<Integer> catchAtTime(int [] t, int [] h, int time) {
    Set<Integer> res = new HashSet<>();
    for (int i = 0; i < t.length; i++)
      if (time >= t[i] && time <= h[i])
        res.add(i);
    return res;
  }

}
