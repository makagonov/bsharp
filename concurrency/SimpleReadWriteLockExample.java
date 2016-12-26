import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;

public class SimpleReadWriteLockExample {

  static class ReadWriteLock {
    private int readersCount;
    private int writersCount;
    private int writersWaitingCount;

    public ReadWriteLock() {
      readersCount = 0;
      writersCount = 0;
      writersWaitingCount = 0;
    }

    public synchronized void readLock() throws InterruptedException {
      while(writersCount > 0 || writersWaitingCount > 0) {
        log("waiting for read");
        wait();
        log("something changed..");
      }
      readersCount++;
      log("acquired read lock");
    }

    public synchronized void writeLock() throws InterruptedException {

      while(readersCount > 0 || writersCount > 0) {
        writersWaitingCount++;
        log("waiting for write");
        wait();
        log("something changed..");
        writersWaitingCount--;
      }
      writersCount++;
      log("acquired write lock");
    }

    public synchronized void readUnlock() {
      readersCount--;
      log("unacquired read lock");
      notifyAll();
    }

    public synchronized void writeUnlock() {
      writersCount--;
      log("unacquired write lock");
      notifyAll();
    }

    private void log(String message) {
      System.out.println(Thread.currentThread().getName() + ": " + message + "; readersCount: " + readersCount + " writersCount: " + writersCount + " writersWaitingCount: " + writersWaitingCount);
    }
  }

  static class Reader implements Runnable {
    private final int sleepTime;
    private final ReadWriteLock rwl;
    public Reader(ReadWriteLock rwl, int sleepTime) {
      this.sleepTime = sleepTime;
      this.rwl = rwl;
    }

    @Override
    public void run() {
      try {
        rwl.readLock();
        Thread.sleep(sleepTime);
        rwl.readUnlock();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }

  static class Writer implements Runnable {
    private final int sleepTime;
    private final ReadWriteLock rwl;
    public Writer(ReadWriteLock rwl, int sleepTime) {
      this.sleepTime = sleepTime;
      this.rwl = rwl;
    }

    @Override
    public void run() {
      try {
        rwl.writeLock();
        Thread.sleep(sleepTime);
        rwl.writeUnlock();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }


  public static void main (String [] args) throws InterruptedException {
    ReadWriteLock rwl = new ReadWriteLock();
    ExecutorService service = Executors.newFixedThreadPool(10);
    Random rnd = new Random();
    for (int i = 0; i < 6; i++) {
      if (rnd.nextInt() % 2 == 0) {
        new Thread(new Reader(rwl, rnd.nextInt(5000) + 1000), "reader_" + i).start();
      } else {
        new Thread(new Writer(rwl, rnd.nextInt(5000) + 1000), "writer_" + i).start();
      }
    }
  }
}
