package paralleljobrunner;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ParallelJobRunner {
  public ParallelJobRunner() {}

  private void dfs(Job job, List<Job> topoSortedJobs, HashSet<Job> visitedJobs) {
    if (visitedJobs.contains(job)) return;
    visitedJobs.add(job);

    for (Job dependency : job.getDependencies()) {
      if (!visitedJobs.contains(dependency)) {
        dfs(dependency, topoSortedJobs, visitedJobs);
      }
    }
    topoSortedJobs.add(job);
  }

  private List<DAGComponent> buildDAGComponents(List<Job> jobs) throws InterruptedException, ExecutionException {
    HashSet<Job> visitedJobs = new HashSet<>();
    List<DAGComponent> dagComponents = new ArrayList<>();
    for (Job job : jobs) {
      if (!visitedJobs.contains(job)) {
        List<Job> topoSortedJobs = new ArrayList<>();
        dfs(job, topoSortedJobs, visitedJobs);
        dagComponents.add(DAGComponent.fromTopologicallySortedList(topoSortedJobs));
      }
    }
    return dagComponents;
  }


  public void runJobs(List<Job> jobs) throws InterruptedException, ExecutionException {
    List<DAGComponent> dagComponents = buildDAGComponents(jobs);
    final ExecutorService executorService = Executors.newFixedThreadPool(10);
    dagComponents.parallelStream().forEach(s -> {
      try {
        s.runTasks(executorService);
      } catch (InterruptedException e) {
        System.err.println("InterruptedException occured");
        e.printStackTrace();
      } catch (ExecutionException e) {
        System.err.println("ExecutionException occured");
        e.printStackTrace();
      }
    });

    executorService.shutdown();
  }

  public static void main (String [] args) throws ExecutionException, InterruptedException {
    List<Job> jobs = IntStream
        .range(0, 10)
        .mapToObj(i -> new Job("job" + i))
        .collect(Collectors.toList());

    jobs.get(1).addDependency(jobs.get(4));
    jobs.get(1).addDependency(jobs.get(5));
    jobs.get(1).addDependency(jobs.get(6));
    jobs.get(5).addDependency(jobs.get(4));
    jobs.get(6).addDependency(jobs.get(4));

    new ParallelJobRunner().runJobs(jobs);
  }

}
