import java.util.*;


public class TaskScheduler621 {
  public static void main(String [] args) {
    System.out.println(new Main().leastInterval("AAAAABCD".toCharArray(), 2));
  }
  public int leastInterval(char[] tasks, int n) {
    if (n == 0) return tasks.length;
    Map<Character, Integer> counts = new HashMap<>();
    for (char t : tasks) {
      counts.put(t, counts.getOrDefault(t, 0) + 1);
    }

    PriorityQueue<Map.Entry<Character, Integer>> queue = new PriorityQueue<>((a, b) -> b.getValue() - a.getValue());
    queue.addAll(counts.entrySet());
    Map<Character, Integer> lastExec = new HashMap<>();
    Map<Integer, Character> exec = new HashMap<>();
    int time = 1;
    while(queue.size() > 0) {
      if (exec.containsKey(time)) {
        time++;
        continue;
      }
      Map.Entry<Character, Integer> entry = queue.poll();
      char c = entry.getKey();
      int charCount = entry.getValue();
      Integer charLastExec = lastExec.get(c);
      int execTime = time;
      if (charLastExec == null || time - charLastExec > n) {
        time++;
      } else {
        execTime = charLastExec + n + 1;
      }
      lastExec.put(c, execTime);
      exec.put(execTime, c);
      
      if (charCount > 1) {
        entry.setValue(charCount - 1);
        queue.add(entry);
      }
    }
    
    //System.out.println(exec);
    
    return Collections.max(lastExec.values());
  }
}
