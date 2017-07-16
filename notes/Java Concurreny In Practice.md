# Chapter 1. IntroductionProcesses are isolated, independently executed programs to which the operating system allocates resources such as memory, file handlers, and security credentials.Threads share process-wide resources such as memory and file handles, but each thread has its own program counter, stack, and local variables. They are also called lightweight processes.Garbage collector runs in one or more dedicated threads.A program with only one thread can run on at most one processor at a time.A complicated, asynchronous workflow can be decomposed into a number of simpler, synchrownous workflows each running in a separate thread, interacting only with each other at specific synchronization points.When a servlet’s service method is called in response to a web request, it can process the request synchronously as if it were a single-threaded program.Unix select and poll system calls?Package java.nio for nonblocking IOContext switch is when the scheduler suspends the active thread temporarily so another thread can run.Timer creates threads to execute deferred tasks.# Chapter 2. Thread safetyWriting thread-safe code is, at its core, about managing access to state, and in particular to shared, mutable state.Informally, an object’s state is its data, stored in state variables.By shared we mean that a variable could be accessed by multiple threads.Whenever more than one thread accesses a given state variable, and one of them might write to it, they all must coordinate their access to it using synchronization.If multiple threads access the same mutable state variable without appropriate synchronization, your program is broken. There are 3 ways to fix it:-	Don’t share the state variable across threads;-	Make state variable immutable. Use synchronization whenever accessing the state variableIt is far easier to design a class to be thread safe than to retrofit it for thread safety later.The better encapsulated your program state, the easier it is to make your program thread-safe and to help maintainers keep it that way.**It is always a good practice first to make your code right, and then make it fast.**Because concurrency bugs are so difficult to reproduce and debug, the benefit of a small performance gain on some infrequently used code path may well be dwarfed by the risk the the program will fail in the field.## 2.1 What is thread safety?At the heart of thread safety is the concept of correctness. Correctness means that a class conforms to its specification. A class is thread safe when it continues to behave correctly when accessed from multiple threads.```Java@ThreadSafepublic class StatelessFactorizer extends GenericServlet implements Servlet {    public void service(ServletRequest req, ServletResponse resp) {        BigInteger i = extractFromRequest(req);        BigInteger[] factors = factor(i);        encodeIntoResponse(resp, factors);    }}````StatelessFactorizer` is stateless: it has no fields and references no fields from other classes. The transient state for a particular computation exists solely in local variables that are stored on the thread’s stack and are accessible only to the executing thread.**Stateless objects are always thread safe.**## 2.2 Atomicity count++ if an example of **_read-modify-write_** operation, the resulting state is derived from the previous state.The most common type of race condition is check-then-act, where a potentially stale observation is used to make a decision on what to do next. In other words, you observe something to be true (file X does not exist), and then take action based on that observation (create X), but in fact the observation could have become invalid between the time you observed it and the time you acted on it (someone else created X in the meantime).### 2.2.3 Compound actionsWe refer collectively to check-then-act and read-modify-write sequences as compound actions: sequences of operations that must be executed atomically in order to remain thread safe.`java.util.concurrenct.atomic.{AtomicLong, AtomicInteger, …}`Where practical, use existing thead-safe objects, like `AtomicLong`, to manage your class’s state. It is simpler to reason about the possible states and state transitions for existing thread-safe objects than it is for arbitrary state variables, and this makes it easier to maintain and verify thread safety.## 2.3 Locking```Java@NotThreadSafepublic class UnsafeCachingFactorizer extends GenericServlet implements Servlet {    private final AtomicReference<BigInteger> lastNumber            = new AtomicReference<BigInteger>();    private final AtomicReference<BigInteger[]> lastFactors            = new AtomicReference<BigInteger[]>();    public void service(ServletRequest req, ServletResponse resp) {        BigInteger i = extractFromRequest(req);        if (i.equals(lastNumber.get()))            encodeIntoResponse(resp, lastFactors.get()); - can change        else {            BigInteger[] factors = factor(i);            lastNumber.set(i); - must be atomic with lastFactors            lastFactors.set(factors);            encodeIntoResponse(resp, factors);        }    }}```To preserve state consistency, update related state variables in a single atomic operation.### 2.3.1 Intrinsic locksJava provides a built-in locking mechanism for enforcing atomicity: the `synchronized` block. A `synchronized` block has two parts: a reference to an object that will serve as the lock, and a block of code to be guarded by that lock.Static synchronized methods use the Class object for the lock.Every java object can implicitly act as a lock for purposes of synchronization; these locks are called intrinsic locks or monitor locks.Intrinsic locks in Java **act as mutexes** (mutually exclusive locks) – at most one thread can own the lock.### 2.3.2 ReentrancyWhen a thread requests a lock tha tis already held by another thread, the requesting thread blocks. But because intrinsic locks are reentrant, if a thread tries to acquire a lock that it already holds, the request succeeds. Reentrancy means that locks are acquired on a per-thread rather than per-invocation basis. Reentrancy is implemented by having a count associated with a thread. When thread acquires a lock – count is 1; when same thread acquires the lock again – count is incremented; when the owning thread exits the synchronized block, the count is decremented. When the count reaches 0 – lock is released.Reentrancy saves us from deadlock in situations like this:```class Widget {    public synchronized void doSomething() {    }}class LoggingWidget extends Widget {    public synchronized void doSomething() {        System.out.println(toString() + ": calling doSomething");        super.doSomething();    }}```## 2.4 Guarding state with locksHolding the lock for the entire duration of a compound action can make that compound action atomic. However, just wrapping the compound action with a synchronized block is not sufficient; if synchronization is used to coordinate access to a variable, it is needed everywhere that variable is accessed. Further, when using locks to coordinate access to a variable, the same lock must be used wherever that variable is accessed.For each mutable state variable that may be accessed by more than one thread, all accesses (even read) to that variable must be performed with the same lock held. In this case, we say that the variable is guarded by that lock.Every shared, mutable variable should be guarded by exactly one lock. Make it clear to maintainers which lock that is.For every invariant that involves more than one variable, all the variables involved in that invariant must be guarded by the same lock.Merely synchronizing every method, as Vector does, is not enough to render compound actions on a Vector atomic:```If (!vector.contains(element))  Vector.add(element);```Additional locking is required above when multiple operations are combined into a compound action.```java@ThreadSafepublic class CachedFactorizer extends GenericServlet implements Servlet {    @GuardedBy("this") private BigInteger lastNumber;    @GuardedBy("this") private BigInteger[] lastFactors;    @GuardedBy("this") private long hits;    @GuardedBy("this") private long cacheHits;    public synchronized long getHits() {        return hits;    }    public synchronized double getCacheHitRatio() {        return (double) cacheHits / (double) hits;    }    public void service(ServletRequest req, ServletResponse resp) {        BigInteger i = extractFromRequest(req);        BigInteger[] factors = null;        synchronized (this) {            ++hits;            if (i.equals(lastNumber)) {                ++cacheHits;                factors = lastFactors.clone();            }        }        if (factors == null) {            factors = factor(i);            synchronized (this) { - not holding lock during expensive computation calculation                lastNumber = i;                lastFactors = factors.clone();            }        }        encodeIntoResponse(resp, factors);    }    void encodeIntoResponse(ServletResponse resp, BigInteger[] factors) {    }    BigInteger extractFromRequest(ServletRequest req) {        return new BigInteger("7");    }    BigInteger[] factor(BigInteger i) {        // Doesn't really factor        return new BigInteger[]{i};    }}```Avoid holding locks during lengthy computations or operations at risk of not completing quickly such as network or console I/O.# Chapter 3. Sharing objectsSynchronization also has another significant, and subtle, aspect: memory visibility. We want not only to prevent one thread from modifying the state of an object when another is using it, but also to ensure that when a thread modifies the state of an object, other threads can actually see the changes that were made.## 3.1 VisiblityThere is no guarantee that the reading thread will see a value written by another thread on a timely basis, or even at all. In order to ensure visibility of memory writes across threads, you must use synchronization.```javapublic class NoVisibility { - BAD EXAMPLE    private static boolean ready;    private static int number;    private static class ReaderThread extends Thread {        public void run() {            while (!ready)                Thread.yield();            System.out.println(number);        }    }    public static void main(String[] args) {        new ReaderThread().start();        number = 42;        ready = true;    }}```2 cases possible above: ready is never true for the launched thread; ready could become true – but number will stay stale, causing 0 to be printed. **The order of changes is unpredictable**.In the absence of synchronization, the compiler, processor, and runtime can do some downright weird things to the order in which operations appear to execute. Attempts to reason about the order in which memory actions “must” happen is insufficiently synchronized multithreaded programs will almost certainly be incorrect.### 3.1.1 Stale dataUnless synchronization is used every time a variable is accessed, it is possible to see a stale value for that variable. Stale data can cause serious and confusing failures such as unexpected exceptions, corrupted data structures, inaccurate computations, and inifite loops.Synchronizing only the setter will not be sufficient – threads calling get would still be able to see stale data.### 3.1.2 Nonatomic 64-bit operations64-bit numbers that are not declared volatile can show totally incorrect value (which could not even potentially be set), because JVM is permitted to treat a 64-bit read or write as two separate 32-bit operations. So, in case of a race condition, half of the bits could be written by one thread, another half – by another.### 3.1.3 Locking and visibilityLocking is not just about mutual exclusion; it is also about memory visibility. To ensure that all threads see the most up-to-date values of shared mutable variables, the reading and writing threads must synchronize on a common lock.### 3.1.4 Volatile variablesWhen a field is declared `volatile`, the compiler and runtime are put on notice that this variable is shared and that operations on it should not be reordered with other memory operations. Volatile variables are not cached in registers or in caches where they are hidden from other processors, so a read of a volatile variable always returns the most recent write by any thread.Volatile is like locking using synchronized, but there is no actual locking, and executing thread cannot block. Volatile reads are actually only slightly more expensive than non-volatile ones.Avoid using volatile variables when verifying correctness would require subtle reasoning about visibility. The most common use of volatile variables is as completion, interruption, or status flag. More care is required in other cases. For instance, it does not support atomicity of ++ operation, unless you guarantee that the variable is written from a single thread.Locking can guarantee both visibility and atomicity; volatile variables can guarantee only visibility.## 3.2 Publication and escapePublishing an object means making it available to code outside of tis current scope, such as by storing a reference to it where other code can find it.An object that is published when it should not have been is said to have escaped.```javaclass UnsafeStates {    private String[] states = new String[]{        "AK", "AL" /*...*/    };    public String[] getStates() {        return states; - states escaped    }}```Any object that is reachable from a published object by following some chain of nonprivate field references and method calls has also been published.```javapublic class ThisEscape {    public ThisEscape(EventSource source) {        source.registerListener(new EventListener() {            public void onEvent(Event e) {                doSomething(e);            }        });    }}```In the example above, when `ThisEscape` publishes the `EventListener`, it implicitly publishes the enclosing `ThisEscape` instance as well, because inner class contains a hidden reference to the enclosing instance.If you are templted to register an event listener or start a thread from a constructor, you can avoid the improper construction by using a private constructor and a public factory (static) method.## 3.3 Thread confinementAccessing shared, mutable data requores using synchronization; one way to avoid this requirement is to not share. If data is only accessed from a single thread, no synchronization is needed. This technique is called thread confinement.Example: JDBC Connection, is not required to be thread safe. Taking connection from pool and returning it when done – thread confinement.### 3.3.2 Stack confinementBasic idea is that local variables are thread confined.Primitive types have no reference – they are stack confined.Using a non-thread object in a within-thread context is still thread-safe.### 3.3.3 ThreadLocalA more formal means of maintaining thread confinement is `ThreadLocal`, which allows you to associate a per-thread value with a value-holding object. It provides getter and setter, which maintains a separate copy of the value for each thread that uses it.```javapublic class ConnectionDispenser {    static String DB_URL = "jdbc:mysql://localhost/mydatabase";    private ThreadLocal<Connection> connectionHolder            = new ThreadLocal<Connection>() {                public Connection initialValue() {                    try {                        return DriverManager.getConnection(DB_URL);                    } catch (SQLException e) {                        throw new RuntimeException("Unable to acquire Connection, e");                    }                };            };    public Connection getConnection() {        return connectionHolder.get();    }}```Conceptually you can think of `ThreadLocal<T>` as holding a `Map<Thread, T>` that stores the thread-specific values. The thread-specific values are stored in the Thread object itself – so when it terminates, the thread-specific values van be garbage collected.## 3.4 Immutability**Immutable objects are always thread-safe.**An object is immutable if:-	Its state cannot be modified after construction-	All its fields are final-	It is properly constructed (the this reference does not escape during construction) Immutable objects can still use mutable objects internally to manage their state:```java@Immutable public final class ThreeStooges {    private final Set<String> stooges = new HashSet<String>();    public ThreeStooges() {        stooges.add("Moe");        stooges.add("Larry");        stooges.add("Curly");    }    public boolean isStooge(String name) {        return stooges.contains(name);    }    public String getStoogeNames() {        List<String> stooges = new Vector<String>();        stooges.add("Moe");        stooges.add("Larry");        stooges.add("Curly");        return stooges.toString();    }}```While the `Set` is mutable, the design does not allow modifying it after construction. Also the variable is `final`.There is difference between an object being immutable and reference to it being immutable. Program state stored in immutable objects can still be replaced with a new instance.### 3.4.1 Final fieldsUse of final fields lets immutable objects be freeled accessed and shared without synchronization.Just as a good practice to make all fields private unless they need greater visibility, it is a good practice to make all fields final unless they need to be mutable.Immutable objects can sometimes provide a weak form of atomicity.Whenever a group of related data items must be acted on atomically, consider creating an immutable holder class for them:```java@Immutablepublic class OneValueCache {    private final BigInteger lastNumber;    private final BigInteger[] lastFactors;    public OneValueCache(BigInteger i,                         BigInteger[] factors) {        lastNumber = i;        lastFactors = Arrays.copyOf(factors, factors.length);    }    public BigInteger[] getFactors(BigInteger i) {        if (lastNumber == null || !lastNumber.equals(i))            return null;        else            return Arrays.copyOf(lastFactors, lastFactors.length);    }}```Immutable objects can be used safely by any thread without additional synchronization, even when synchronization is not used to publish them.### 3.5.3 Safe publication idiomsTo publish an object safely, both the reference to the object and the objects’ state must be made visible to other threads at the same time. A properly constructed object can be safely published by:-	Initializing an object reference from a static initializer;-	Storing a reference to it into a volatile field or AtomicReference-	Storing a reference to it into a final  field of a properly constructed object-	Storing a reference to it into a  field that is properly guarded by a lockPublishing objects is safe, if you add them using HashTable, SynchronizedMap, ConcurrentMap, Vector, ….Using a static initializer is often the easiest and safest way to publish an object:`Public static Holder holder = new Holder(42);`**Static initializers are executed by the JVM at class initialization time; there is internal synchronization.**### 3.5.4 Effectively immutable objectsObjects that are not technically immutable, but whose state will not be modified after publication, are called **effectively immutable**. They can be used without additional synchronization.For example, Date is immutable, but if you use it as if it were immutable, you can skip locking when sharing it across threads.### 3.5.6 Sharing objects safelyThe most useful policies for using and sharing objects in a concurrent program are:-	Thread-confined: A thread-confined object is owned exclusively by and confined to one thread, and can be modified by its owning thread-	Shared read-only: A shared read-only object can be accessed concurrently by multiple threads without additional synchronization, but cannot be modified by any thread. Shared read-only objects include immutable and effectively immutable objects-	Shared thread-safe: A thread-safe object performs synchronization internally, so multiple threads can freely access it through its public interface without further synchronization-	Guarded: A guarded object can be accessed only with a specific lock held. Guarded objects include those that are encapsulated within other thread-safe objects and published objects that are known to be guarded by a specific lock# Chapter 4. Composing objects# Chapter 5. Building blocksSynchronized collections – `HashTable`, `Vector`, and other in `Collections.synchronized`…Basic operations are synchronized, but some compound actions can also by synchronized by the collection itself as a lock. This actually creates an overhead.Alternative to locking the collection is to create a copy, and operate on it. It is a deal of a tradeoff.ConcurrentModificationException: iterators returned by synchronized collections do not deal with concurrent modification. There is a “count” (the check is done without sync, so there is a risk of getting a stale count) associated with the collection, and if it changes – the exception is thrown.You have to use locking wherever a shared collection might be iterated, like in System.out.println() – it will internally iterate the elements, and can throw ConcurrentModificationException.Iteration is also indirectly invoked by hashCode(), containsAll(), removeAll(), etc.Concurrent collections can replace synchronized collections:-	They predefine atomic compound actions, like putIfAbsent, etc.-	You cannot use the collection as a lock – the internal lock is different for this collections-	Size() and isEmpty() are estimates, rather than exact; they are not actually as useful in concurrent apps as get(), put(),remove(), etc. -	Arbitrary many readers can access the collection concurrently, readers can access it with writers concurrently, but only a limited number of writers can make changes-	It never throws ConcurrentModificationException, and it provides such iterators, which are weakly consistent – traverses the elements as they existed when the iterator was constructed, and may reflect changes after the construction of the iteratorCopyOnWriteArrayList: returned iterators don’t throw the ConcurrentModificationException, and return the elements exactly as they were at the time the iterator was constructed. Reasonable to use when iteration is more common than modification.Blocking queues can be used in a producer-consumer pattern:-	If bounded, they block the threads that try to add objects-	If empty, block threads that try to get from it-	Provide an “offer” method, which returns a failure status if item cannot be enqueuedProducer-consumer pattern separate the notions of work generation and work exection. Just like in a dish washing: one person washes the dishes, another dries when they are available.There must be a good reasoning of how many produces and consumers to have, because having more of ones can cause the others wait for a long time.Implementations of blocking queues: LinkedBlockingQueue (like LinkedList) and ArrayBlockingQueue (like ArrayList), and PriorityBlockingQueue (like PriorityQueue).Synchronous queue – maintains no storage space, but maintains a list of queued threads waiting to enqueue and dequeue.```javapublic class ProducerConsumer {  static class FileCrawler implements Runnable {    private final BlockingQueue<File> fileQueue;    private final FileFilter fileFilter;    private final File root;    public FileCrawler(BlockingQueue<File> fileQueue,                       final FileFilter fileFilter,                       File root) {      this.fileQueue = fileQueue;      this.root = root;      this.fileFilter = new FileFilter() {        public boolean accept(File f) {          return f.isDirectory() || fileFilter.accept(f);        }      };    }    private boolean alreadyIndexed(File f) {      return false;    }    public void run() {      try {        crawl(root);      } catch (InterruptedException e) {        Thread.currentThread().interrupt();      }    }    private void crawl(File root) throws InterruptedException {      File[] entries = root.listFiles(fileFilter);      if (entries != null) {        for (File entry : entries)          if (entry.isDirectory())            crawl(entry);          else if (!alreadyIndexed(entry))            fileQueue.put(entry);      }    }  }  static class Indexer implements Runnable {    private final BlockingQueue<File> queue;    public Indexer(BlockingQueue<File> queue) {      this.queue = queue;    }    public void run() {      try {        while (true)          indexFile(queue.take());      } catch (InterruptedException e) {        Thread.currentThread().interrupt();      }    }    public void indexFile(File file) {      // Index the file...    };  }  private static final int BOUND = 10;  private static final int N_CONSUMERS = Runtime.getRuntime().availableProcessors();  public static void startIndexing(File[] roots) {    BlockingQueue<File> queue = new LinkedBlockingQueue<File>(BOUND);    FileFilter filter = new FileFilter() {      public boolean accept(File file) {        return true;      }    };    for (File root : roots)      new Thread(new FileCrawler(queue, filter, root)).start();    for (int i = 0; i < N_CONSUMERS; i++)      new Thread(new Indexer(queue)).start();  }}```Problem with the file crawler above: threads don’t know when to stop.Serial thread confinement: handing off ownership of objects from producers to consumers. Object pools exploit serial thread confinement, lending an object to a requesting thread.Deques – allow inserting both at the tail, and at the head. Kind of stack and queue in the same collection. BlockingDeque can be used for “job stealing”, when one thread can steal work from another whose queue is not empty. Stealing is done from the tail.**When a method can throw `InterruptedException`, it is telling you that it is a blocking method**. If a thread is interrupted, it should be stopped when it’s comfortable. One thread cannot stop another thread, but can request to stop doing what it’s doing.2 ways to handle interruption:-	Propagate it by not catching, so that the upper level class took care of it-	Restore the interrupt. Sometimes you cannot throw the InterruptedException, but will catch it, and call interrupt() on itself, so that the code higher up the stack could see that an interrupt was issued.Synchronizers  is an object that coordinates the control flow of thread based on its state. Blocking queues can act as synchronizers. Other synchronizers: latches, barriers, and semaphores.Latches. Binary latch can be used for some events that must happen before threads start to execute. It’s like a gain, when it’s open – it cannot close again. Non-binary latches can be used to wait for the last thread to finish before doing something else. ```javapublic class TestHarness {  public long timeTasks(int nThreads, final Runnable task)      throws InterruptedException {    final CountDownLatch startGate = new CountDownLatch(1);    final CountDownLatch endGate = new CountDownLatch(nThreads);    for (int i = 0; i < nThreads; i++) {      Thread t = new Thread() {        public void run() {          try {            startGate.await();            try {              task.run();            } finally {              endGate.countDown();            }          } catch (InterruptedException ignored) {          }        }      };      t.start();    }    long start = System.nanoTime();    startGate.countDown();    endGate.await();    long end = System.nanoTime();    return end - start;  }}```**Futures** allow to make async calls. FutureTask conveys the result from the executing thread to the calling thread.**Semaphores.** Binary semaphores can be used as non-reentrant mutexes. Counting semaphores – limiting the number of threads that can access a resource. For example, BoundedHashSet:Blocking is done in both cases: when bound is reached and acquire is call, and when release() is called while nobody acquired anything.```javapublic class BoundedHashSet <T> {  private final Set<T> set;  private final Semaphore sem;  public BoundedHashSet(int bound) {    this.set = Collections.synchronizedSet(new HashSet<T>());    sem = new Semaphore(bound);  }  public boolean add(T o) throws InterruptedException {    sem.acquire();    boolean wasAdded = false;    try {      wasAdded = set.add(o);      return wasAdded;    } finally {      if (!wasAdded)        sem.release();    }  }  public boolean remove(Object o) {    boolean wasRemoved = set.remove(o);    if (wasRemoved)      sem.release();    return wasRemoved;  }}```**Barriers**: similar to latches, but are waiting for threads, rather than events. `CyclicBarrier`: accepts a runnable, which will be run after all threads are ready, but before executing the threads.Another form of barrier: `Exchanger`. Similar to a binary semaphore.Implementing a concurrent cache:-	Given interface `Computable<A, V>` with `V compute(A arg)`, which defines an expensive computation-	Use concurrent hash map-	Store `Future<V>` instead of V-	Use `putIfAbsent` to have atomicity of get + put-	When calling `Future.get()` – catch `CancellationException`, and remove the key from the cache, so that it was reinserted-	Use `while(true)` to be able to retry. Potentially use `MAX_RETRY_COUNT````javainterface Computable<A, V> {  V compute(A arg) throws InterruptedException;}public class Memoizer<A, V> implements Computable<A, V> {  private final Computable<A, V> c;  private final int MAX_RETRIES;  private final ConcurrentHashMap<A, Future<V>> cache = new ConcurrentHashMap<>();    public Memoizer(Computable<A, V> c, int maxRetries) { this.c = c; this.MAX_RETRIES = maxRetries; }    @Override  public V compute(final A arg) throws InterruptedException {    for (int attempt = 1; attempt <= MAX_RETRIES; attempt++) {      Future<V> f = cache.get(arg);      if (f == null) {        FutureTask<V> ft = new FutureTask<V>(() -> c.compute(arg));        // reassigning f, because some other thread could already put a future into the map        // while the FutureTask was being composed         f = cache.putIfAbsent(arg, ft);        //if if is null, we know for sure that f is currently in the  map        if (f == null) { f = ft; ft.run(); }      }      try {        return f.get();      } catch (CancellationException e) {        //calling remove() with two arguments - will remove from map only if value is equal to f        //because other thread could potentially put another value already        cache.remove(arg, f);      } catch (ExecutionException e) {        e.printStackTrace();      }    }    throw new IllegalStateException("Could not computer value for argument " + arg + " in " + MAX_RETRIES + " attempts");  }}```# Chapter 6.Task execution Ideally, tasks are independent activities: work that doesn’t depend on the state, result, or side effects of other tasks.Disadvantages of unbounded thread creation:-	Thread lifecycle overhead – expensive to initialize-	Resource consumption – when more runnable threads than available processors – threads sit idle. If you have enough threads to keep all the CPU busy, creating more threads won’t help and may even hurt.-	Stability – There is a limit on how many threads can be created.`Executor` framework  provides a standard means of decoupling task submission from task execution.```javapublic interface Executor {	Void execute(Runnable command);}```Thread pool is tightly bound to a work queue holding tasks waiting to be executed.All the thread pools (newFixed…, singleThreaded…) add new thread if it dies due to an unexpected `Exception`.Executors should be able to be shut down, both gracefully and abruptly. To address this issue of execution service lifecycle, ExecutorService exists, which adds a number of methods for lifecycle management:```javapublic interface ExecutorService extends Executor {  Void shutdown(); - gracefull shutdown  List<Runnable> shutDownNow(); - abrupt shutdown  Boolean isShutdown();  Boolean isTerminated();  Boolean awaitTermination(long timeout, TimeUnit unit) throws InterruptedException;…}````ExecutorService` lifecycle has 3 states: running, shutting down, terminated.Consided `ScheduledThreadPool` instead of Timer. If timer thread throws an exception – no new thread is spawned. If you need to build your own scheduling, consider using `DelayedQueue` ( let’s you take an element only if its delay has expired) and `BlockingQueue`.**`Runnable` cannot return a value or throw checked exceptions****`Callable` is a better abstraction – call returns a value, and it can throw an exception.**In the `Executor` framework, tasks that have been submitted but not yet started can always be cancelled, and tasks that have started can sometimes be cancelled if they are responsive to interruption.`Future` represents the lifecycle of a task and provides methods to test whether the task has complted or been cancelled, retrieve its result, and cancel the task.```javapublic interface Callable<V> {  V call() throws Exception;}public interface Future<V> {  Boolean cancel(Boolean mayInterruptIfRunning);  Boolean isCancelled();  Boolean isDone();  V get() throws InterruptedException, ExecutionException, CancellationException;  V get(long timeout, Timeounit unit) throws …}````ExecutorService.submit` always returns a `Future` – so you can submit runnable, callable and get back a future.If you divide tasks A and B between two workers but A takes 10 minutes as long as B, you’ve only speeded up the total process by 9%. CompletionService  - combines Executor and BlockingQueue. You can submit Callable and use the queue-like methods take and poll to retrieve completed results, package as Futures.Implementation – `ExecutorCompletionService` – blocking queue created in constructor to hold completed results. Method done is called when tasks is completed. Submitted tasks are wrapped with `QueuingFuture` – that puts result on `BlockingQueue` in done.Placing time limit on tasks:The timed version of `Future.get` returns as soon as result is ready, and throws `TimeoutException` if the result is not ready. Has nothing to do with the internal queue – it’s the timeout for the `get` method. If a timed get completes with the `TimeoutException` – you can cancel the task through the `Future`. 

# Chapter 7. Cancellation and Shutdown

Java does not provide any mechanism for safely forcing a thread to stop what it is doing. Instead, it provides interruption. The cooperative approach is required because we reraly want a task to stop *immediately, *since that could leave shared data structures in inconsistent state.

One such cooperative mechanism is setting a "cancellation request” flag that the task checks periodically:

```java
...
private volatile boolean cancelled;

public void run() {
    BigInteger p = BigInteger.ONE;
    while (!cancelled) {
        p = p.nextProbablePrime();
        synchronized (this) {
            primes.add(p);
        }
    }
}

public void cancel() {
    cancelled = true;
}
...
```

The approach above would not work well if the task calls a blocking method (`BlockingQueue.put`) - the task might never terminate.
`Thread.interrupted()`- **caution!** returns the status and clears it!
Use `Thread.currentThread().isInterrupted()` instead

Blocking library methods like `Thread.sleep` and `Object.wait` try to detect when a thread has been interrupted and return early. They clear **the interrupted flag** and throw `InterruptedException`. JVM does not guarantee when it happens, but it usually happens quickly

If a thread is interrupted when it is not blocked, its interrupted status is set, and it is up to the activity being canceled to poll the interrupted status to detect interruption. Calling `interrupt` does not stop necessarily stop the target thread from what it's doing, but delivers the message that interrupted has been requested.

Instead of using a volatile flag:
```Java
…
while(!Thead.currentThread().isInterrupted()) {
 ...
}
…
public void cancel() {
 interrupt();
}
```

It is important to distinguish between how _tasks_ and _threads_ should react to interruption. A single interrupt request may have more than one desired recipient - interrupting a worker thread in a thread pool may can mean both "cancel the current task" and "shut down the worker thread".

Tasks do not execute threads they own - the borrow threads from the pool. Code that doesn't own the thread should be carefuly to preserve the interrupted status so that the owning code can eventually act on it.

Most blocking library methods simply throw InterruptedException - letting it bubble up to the thread owner as soon as possible. So task can wait to finish some job, then in the `catch` block - call `Thread.currentThread().interrupt()` to restore the flag.

**Because each thread has its own interruption policy, you should not interrupt a thread unless you know what interruption means to that thread.**

When you are calling interruptible blocking method, such as Thread.sleep or BlockingQueue.put, there are a few strategies:
* Propagate exception (add `throws InterruptedException`), making your method a interruptible blocking method - does not work for `Runnable`
* Restore the interruption status so that code higher up on the call stack can deal with it

Activities that do not support cancellation but still call interruptible blocking methods will have to call them in a loop - retrying when interruption is detected:
```Java
public Task getNextTask(BlockingQueue<Task> queue) {
    boolean interrupted = false;
    try {
        while (true) {
            try {
                return queue.take();
            } catch (InterruptedException e) {
                interrupted = true;
                // fall through and retry
            }
        }
    } finally {
        if (interrupted)
            Thread.currentThread().interrupt();
    }
}
```

If code does not call interruptible - poll current thread's interrupted status

Cancelling a task using Future:
```java
public class TimedRun {
    private static final ExecutorService taskExec = Executors.newCachedThreadPool();

    public static void timedRun(Runnable r,
                                long timeout, TimeUnit unit)
            throws InterruptedException {
        Future<?> task = taskExec.submit(r);
        try {
            task.get(timeout, unit);
        } catch (TimeoutException e) {
            // task will be cancelled below
        } catch (ExecutionException e) {
            // exception thrown in task; rethrow
            throw launderThrowable(e.getCause());
        } finally {
            // Harmless if task already completed
            task.cancel(true); // interrupt if running
        }
    }
}
```

If a thread is blocked performing synchronous socket I/O or waiting to acquire an intrinsic lock, interruption has no effect other than setting the interrupted status. How to handle:
* Synchronous I/O in java.io - `read` and `write` in `InputStream` and `OutputStream` are not responsive to interruption, but closing the underlying socket makes any thread blocked in `read` or `write` throw `SocketException`:
```java
public class ReaderThread extends Thread {
    private static final int BUFSZ = 512;
    private final Socket socket;
    private final InputStream in;

    public ReaderThread(Socket socket) throws IOException {
        this.socket = socket;
        this.in = socket.getInputStream();
    }

    public void interrupt() {
        try {
            socket.close();
        } catch (IOException ignored) {
        } finally {
            super.interrupt();
        }
    }

    public void run() {
        try {
            byte[] buf = new byte[BUFSZ];
            while (true) {
                int count = in.read(buf);
                if (count < 0)
                    break;
                else if (count > 0)
                    processBuffer(buf, count);
            }
        } catch (IOException e) { /* Allow thread to exit */
        }
    }

    public void processBuffer(byte[] buf, int count) {
    }
}
```
* Lock acquisition: there is nothing you can do, **unless explicit Lock classes are used, which offer `lockInterruptibly` method**

In the example below:
```java
synchronized(LogService.this) {
  if (isShutdown && writesPending == 0) {
    break;
  }
  String message = queue.take();
  synchronized(LogService.this) {
    writesPending--;
  }
}
```
We don't want to hold a lock when `queue.take()` is called, because `queue.take()` can block - resulting in other threads using the `LogService` to block

Another way to convince a producer-consumer service to shut down is with a poison pill. With FIFO queues, consumers ensure that consumers finish the work on their queue before shutting down; producers should not submit any work after the poison pill. Poison pill will work only when the number of consumers and producers is known. For `N` producers and 1 consumer - consumer stops when it receives `N` pills. For `M` consumers - each producer should put `M` poison pills. Also the queue should be **unbounded**.
Example: IndexingService:
```java
public class IndexingService {
    private static final int CAPACITY = 1000;
    private static final File POISON = new File("");
    private final IndexerThread consumer = new IndexerThread();
    private final CrawlerThread producer = new CrawlerThread();
    private final BlockingQueue<File> queue;
    private final FileFilter fileFilter;
    private final File root;

    public IndexingService(File root, final FileFilter fileFilter) {
        this.root = root;
        this.queue = new LinkedBlockingQueue<File>(CAPACITY);
        this.fileFilter = new FileFilter() {
            public boolean accept(File f) {
                return f.isDirectory() || fileFilter.accept(f);
            }
        };
    }

    private boolean alreadyIndexed(File f) {
        return false;
    }

    class CrawlerThread extends Thread {
        public void run() {
            try {
                crawl(root);
            } catch (InterruptedException e) { /* fall through */
            } finally {
                while (true) {
                    try {
                        queue.put(POISON);
                        break;
                    } catch (InterruptedException e1) { /* retry */
                    }
                }
            }
        }

        private void crawl(File root) throws InterruptedException {
            File[] entries = root.listFiles(fileFilter);
            if (entries != null) {
                for (File entry : entries) {
                    if (entry.isDirectory())
                        crawl(entry);
                    else if (!alreadyIndexed(entry))
                        queue.put(entry);
                }
            }
        }
    }

    class IndexerThread extends Thread {
        public void run() {
            try {
                while (true) {
                    File file = queue.take();
                    if (file == POISON)
                        break;
                    else
                        indexFile(file);
                }
            } catch (InterruptedException consumed) {
            }
        }

        public void indexFile(File file) {
            /*...*/
        };
    }

    public void start() {
        producer.start();
        consumer.start();
    }

    public void stop() {
        producer.interrupt();
    }

    public void awaitTermination() throws InterruptedException {
        consumer.join();
    }
}
```

If a method needs to run a batch of tasks and does not return until all of them finished - we can use a private Executor whose lifetime is bounded by that method:

```java
public boolean checkMail(Set<String> hosts, long timeout, TimeUnit unit)
    throws InterruptedException {
  ExecutorService exec = Executors.newCachedThreadPool();
  final AtomicBoolean hasNewMail = new AtomicBoolean(false);
  try {
    for (final String host : hosts)
      exec.execute(() -> {
        if (checkMail(host))
          hasNewMail.set(true);
      });
  } finally {
    exec.shutdown();
    exec.awaitTermination(timeout, unit);
  }
  return hasNewMail.get();
}
```

When tasks are shutdown in executor using shutdownNow: executor attempts to cancel the tasks currently in progress and **return a list of tasks that never started**. However, there is no general way to find out which tasks started, but did not complete:
```java
public class TrackingExecutor extends AbstractExecutorService {
  private final ExecutorService exec;
  private final Set<Runnable> tasksCancelledAtShutdown =
      Collections.synchronizedSet(new HashSet<Runnable>());

  public TrackingExecutor(ExecutorService exec) {
    this.exec = exec;
  }

  public List<Runnable> getCancelledTasks() {
    if (!exec.isTerminated())
      throw new IllegalStateException(/*...*/);
    return new ArrayList<Runnable>(tasksCancelledAtShutdown);
  }

  public void execute(final Runnable runnable) {
    exec.execute(() -> {
      try {
        runnable.run();
      } finally {
        if (isShutdown()
            && Thread.currentThread().isInterrupted())
          tasksCancelledAtShutdown.add(runnable);
      }
    });
  }
}

```

Web crawler using the TrackingExecutor from above:
```Java
public abstract class WebCrawler {
    private volatile TrackingExecutor exec;
    @GuardedBy("this") private final Set<URL> urlsToCrawl = new HashSet<URL>();

    private final ConcurrentMap<URL, Boolean> seen = new ConcurrentHashMap<URL, Boolean>();
    private static final long TIMEOUT = 500;
    private static final TimeUnit UNIT = MILLISECONDS;

    public WebCrawler(URL startUrl) {
        urlsToCrawl.add(startUrl);
    }

    public synchronized void start() {
        exec = new TrackingExecutor(Executors.newCachedThreadPool());
        for (URL url : urlsToCrawl) submitCrawlTask(url);
        urlsToCrawl.clear();
    }

    public synchronized void stop() throws InterruptedException {
        try {
            saveUncrawled(exec.shutdownNow());
            if (exec.awaitTermination(TIMEOUT, UNIT))
                saveUncrawled(exec.getCancelledTasks());
        } finally {
            exec = null;
        }
    }

    protected abstract List<URL> processPage(URL url);

    private void saveUncrawled(List<Runnable> uncrawled) {
        for (Runnable task : uncrawled)
            urlsToCrawl.add(((CrawlTask) task).getPage());
    }

    private void submitCrawlTask(URL u) {
        exec.execute(new CrawlTask(u));
    }

    private class CrawlTask implements Runnable {
        private final URL url;

        CrawlTask(URL url) {
            this.url = url;
        }

        private int count = 1;

        boolean alreadyCrawled() {
            return seen.putIfAbsent(url, true) != null;
        }

        void markUncrawled() {
            seen.remove(url);
            System.out.printf("marking %s uncrawled%n", url);
        }

        public void run() {
            for (URL link : processPage(url)) {
                if (Thread.currentThread().isInterrupted())
                    return;
                submitCrawlTask(link);
            }
        }

        public URL getPage() {
            return url;
        }
    }
}
```

**Handling abnormal thread termination**
The leading cause of premature thread death is RuntimeException. They are not caught and propagated all the way up the stack, at which point the default behavior is to print a stack trace on the console and let the thread terminate.

Whenever you call another method - you take a leap of faith that it will return normally or throws one of the checked exceptions its signature decalares.

**Shutdown hooks**
In orderly shutdown, JVM starts all shutdown hooks - which are also threads. The rest of the threads continue to work. Order of shutdown hooks is not guaranteed. When all shutdown hooks have completed, JVM can run finalizers (if enabled), and then halt. 
Shutdown hooks should be thread safe and should not make assumptions about the state of application.
Shutdown hooks can be used for service or application cleanup (deleting temp files, cleaning up resources).
In order not to rely on the same resource by multiple shutdown hooks - we can use a single shutdown hook and execute everything sequentially.

# Chapter 8 Applying thread pool
Single threaded pools make stronger promises about concurrency than do arbitrary thread pools. They guarantee that tasks are not executed concurrently, which allows you to relax the thread safety of task code.
`ThreadLocal` allows each thread to have its own private "version" of a variable, but be careful, because thread pools **reuse threads**, and use it only if thread-local value has a lifetime that is bounded by that of a task.

Thread pools work best when tasks are homogeneous and independent.
Mixing long-living and short-living tasks risks "clogging" the pool unless it is very large.
Tasks that depend on other tasks risks deadlock **unless the pool is unbounded**.

If tasks that depend on other tasks execute in a thread pool, they can deadlock.
In a single-threaded executor, a task that submits another task to the esame executor and waits for its result will always deadlock. This is called a **thread starvation deadlock.**

### Long running tasks
One technique that can mitigate the ill effects of long-running tasks is for tasks to use timed resource waits instead of unbounded waits. Thread.join, BlockingQueue.put, CountDownLatch.await, etc have a timed version of the method.

### Managing queued tasks
If arrival rate for new requests exceeds the rate at which they can be handled, requests will still be queued up. If tasks queue up quickly, you will eventually have to throttle the arrival rate to avoid running out of memory.

The default for `newFixedThreadPool` and `newSingleThreadExecutor` is to use an unbounded `LinkedBlockingQueue`; queue could grow without bound.

Alternative is to use `SynchronousQueue`, if rejection is accepted or the pool is unbounded. 

## Extending ThreadPoolExecutor
`ThreadPoolExecutor` was designed for extension, providing several "hooks" for subclasses to override - `beforeExecute`, `afterExecute`, and `terminated`.
`beforeExecute` and `afterExecute` are called in the thread that executes the task. `afterExecute` executes if task completed normally or threw an exception (but not `Error`). If `beforeExecute` throws a `RuntimeException`, the task is not executed and `afterExecute` is not called.

## Parallelizing recursive algorithms

```Java
 public <T> Collection<T> getParallelResults(List<Node<T>> nodes)
            throws InterruptedException {
        ExecutorService exec = Executors.newCachedThreadPool();
        Queue<T> resultQueue = new ConcurrentLinkedQueue<T>();
        parallelRecursive(exec, nodes, resultQueue);
        exec.shutdown(); //graceful, will wait for completion
        exec.awaitTermination(Long.MAX_VALUE, TimeUnit.SECONDS); //will wait forever TODO Figure out why
        return resultQueue;
    }
```

`private final AtomicInteger taskCount = new AtomicInteger(0);` - incrementing when task is submitted, and decrementing when finished. This way if count = 0, it means that we can stop. **Good way to stop web crawler**

**Still, copy ConcurrentPuzzleSolver.java, Puzzle.java, PuzzleNode.java, PuzzleSolver.java, int a separate project, and play with it!!!**

# Avoiding liveness Hazards
## Deadlocks
When deadlocks do manifest themselves, it is often at the worst possible time - under heavy production load.
**A program will be free of lock-ordering deadlocks if all threads acquire the locks they need in a fixed global order.**

When obtaining multiple locks for objects, we can use System.identityHashCode(obj), and obtain locks (synchronized(obj1)...) in the order. In rare cases, they will be the same - so we can use another tiObject = new Object() as a lock.

You should always try to use open calls - i.e., calling method without holding a lock, because you never know what other methods are doing.

A program that never acquires more than one lock at a time cannot experience lock-ordering deadlock.

Another technique for detecting and recovering from deadlocks is to use the timed `tryLock` of the explicit `Lock` classes. **This is one of the reasons to use explicit locks**. Though when timed lock acquisition fails - we cannot know why: maybe the method was taking too long, or there was an infinite loop. 

## Starvation
Something related to priorities of threads. Avoid the temptation to use thread priorities, since they increase platform dependence.

## Livelock
Livelock is a form of liveness failure in which a thread, while not blocked, still cannot make progress because it keeps retrying an operation that always fails.
Example: when two overly polite people are walking in opposite direction: each steps out of the other's way, and now they're again in each other's way.
The solution to a variety of livelock is to **introduce some randomness** into the retry mechanism.

# Chapter 11. Performance and Scalability
If there are more runnable threads than CPUs, eventually the OS will preemt one thread so that another can use the CPU. This causes _context switch_, which requires saving the execution context of the currently running thread and restoring the execution context of the newly scheduled thread.

Context switches are not free; threads scheduling requires manipulating shared data structures in the OS and JVM. Furthermore, when a thread is switched in, the data it needs is unlikely to be in the **local processor cache**, so a context switch causes a flurry of cache misses, and thus the reasons that schedulers give each runnable thread a certain minimum time quantum even when many other threads are waiting: **it amortizes the cost of context switch over more interrupted execution time, improving overall throughput**.

Synchronization by one thread can also affect the performance of other threads. Synchronization creates traffic on the shared memory bus, which has limited bandwidth and is shared across all processors. If threads must compete for synchronization bandwidth, all threads using synchronization will suffer.

Two factors influence the likelihood of contention of a lock: **how often that lock is requested and how long it is held once acquired**.

An effective way of reducing lock contention is to hold it as briefly as possible, by **moving code that doesn't require the lock out of synchronized blocks**.

The other way to reduce the fraction of time that a lock is held is to have threads ask for it less often. If A and B are **independent** and are guarded by the same lock, they could be split to be held by two separate locks.

Lock stripping: concurrent hash map, has 16 locks for each of the 16 buckets..

Btw, CHM maintains a separate counter for each bucket, also guarded by the bucket lock, and each time `size()` is called, the sum of all counters is obtained. Might not be fully up to date because of that, but Java avoids **a hot field** this way.















