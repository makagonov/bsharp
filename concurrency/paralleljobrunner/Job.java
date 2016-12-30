package paralleljobrunner;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.Callable;

public class Job implements Callable<Void>{
  String id;
  private Set<Job> dependsOn;
  public Job(String id) {
    this.id = id;
    dependsOn = new HashSet<>();
  }

  public void addDependency(Job job) {
    dependsOn.add(job);
  }

  public void addDependencies(Set<Job> d) {
    dependsOn.addAll(d);
  }

  @Override
  public int hashCode() {
    return id.hashCode();
  }

  @Override
  public boolean equals(Object obj) {
    Job other = (Job)obj;
    return id.equals(other.id);
  }

  public Set<Job> getDependencies() {
    return new HashSet<>(dependsOn);
  }

  public boolean sameDependencies(Job other) {
    Set<Job> otherDependencies = other.getDependencies();
    if (dependsOn.size() != otherDependencies.size()) {
      return false;
    }
    return otherDependencies.containsAll(dependsOn);
  }

  @Override
  public String toString() {
    return id;
  }

  @Override
  public Void call() throws Exception {
    System.out.println("executing job " + this.id + " inside thread " + Thread.currentThread().getId());
    Random rnd = new Random();
    try {
      Thread.sleep(rnd.nextInt(5000) + 1000);
    } catch (InterruptedException e) {
      System.out.println("job " + this.id + " was interrupted...");
    }
    return null;
  }
}
