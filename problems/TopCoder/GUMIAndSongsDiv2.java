import java.util.*;

public class GUMIAndSongsDiv2 {

  public int maxSongs(int[] duration, int[] tone, int T) {
    int best = 0;
    int n = duration.length;
    
    Song [] allSongs = new Song[n];
    for (int i = 0; i < n; i++)
      allSongs[i] = new Song(duration[i], tone[i]);
    
    Arrays.sort(allSongs);
    for (int mask = 1; mask < (1<<n); mask++) {
      Song prev = null;
      int totalTime = 0;
      int songsCount = 0;
      for (int i = 0; i < n; i++) {
        if ((mask&(1<<i)) > 0) {
          songsCount++;
          totalTime += allSongs[i].duration;
          if (prev != null)
            totalTime += prev.getDiff(allSongs[i]);
          prev = allSongs[i];
        }
      }
      if (totalTime <= T && songsCount > best)
        best = songsCount;
    }
    return best;
  }
  
  class Song implements Comparable<Song>{
    int duration;
    int tone;
    public Song(int d, int t) {
      this.duration = d;
      this.tone = t;
    }
    @Override
    public int compareTo(Song o) {
      return this.tone - o.tone;
    }
    
    public int getDiff(Song o) {
      return Math.abs(this.tone - o.tone);
    }
  }

}
