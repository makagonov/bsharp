package paralleljobrunner;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.*;

class DAGComponent {
  ArrayList<Set<Job>> levels;
  private DAGComponent() {
    levels = new ArrayList<>();
  }

  private void addLevel(Set<Job> level) {
    levels.add(level);
  }

  public int getNumLevels() {
    return levels.size();
  }

  public Set<Job> getLevelAt(int index) {
    if (index >= levels.size()) {
      return null;
    }
    return new HashSet<>(levels.get(index));
  }

  public static DAGComponent fromTopologicallySortedList(List<Job> topoSortedJobs) {
    if (topoSortedJobs == null || topoSortedJobs.size() == 0)
      throw new IllegalArgumentException("topoSortedJobs list cannot be empty or null");

    DAGComponent dagComponent = new DAGComponent();
    for (int i = topoSortedJobs.size() - 1; i >= 0; i--) {
      Job job = topoSortedJobs.get(i);
      for (Job dep : job.getDependencies()) {
        job.addDependencies(dep.getDependencies());
      }
    }

    boolean [] used = new boolean[topoSortedJobs.size()];
    for (int i = 0; i < topoSortedJobs.size(); i++) {
      if (used[i]) continue;
      Job job = topoSortedJobs.get(i);
      Set<Job> nextLevel = new HashSet<>();
      nextLevel.add(job);
      for (int j = i + 1; j < topoSortedJobs.size(); j++) {
        Job candidate = topoSortedJobs.get(j);
        if (!used[j] && candidate.sameDependencies(job)) {
          used[j] = true;
          nextLevel.add(candidate);
        }
      }
      dagComponent.addLevel(nextLevel);
    }

    return dagComponent;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < levels.size(); i++) {
      sb.append("level " + i + ": " + levels.get(i) + "\n");
    }
    return sb.toString();
  }

  public void runTasks(ExecutorService executorService) throws InterruptedException, ExecutionException {
    System.out.println("starting tasks for dag component " + levels);
    for (Set<Job> level : levels) {
      List<Future<Void>> results = executorService.invokeAll(level);
      //waiting for all the jobs at the level to complete
      //before proceeding to the next level
      for (Future<Void> result : results)
        result.get();
    }
  }
}
