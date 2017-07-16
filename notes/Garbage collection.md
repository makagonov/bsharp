JVM is an abstract computing machine. There is JVM implementation for each operating system, and it translates the Java programming instructions and commands that run on the local operating system. This way Java programs achieve platform independence.

Any language with functionality that can be expressed in terms of a valid class file can be hosted by the Java virtual machine (used by implementors of Groovy, Scala, etc.)

Typically, when tuning a Java application, the focus is either on responsiveness – how quickly system responds, or on throughput – how much work it can do in specific amount of time (per hour, …)

JVM has generational garbage collection. Three generations: Young generation (Eden, survivor space 1, survivor space 2), Tenured generation, and Permanent generation (used for Classes).

When Young generation fills up – **minor garbage collection **is performed. This is always “Stop the world” event.

When Old generation fills up – **major garbage collection** is performed.

Workflow of objects as of minor garbage collections:

1. Get to Eden space

2. Cleaning up Eden, moving from Eden to First Survivor Space (S0)

3. Cleaning up Eden, moving from Eden to S1, cleaning up S0, moving from S0 to S1

4. Cleaning up Eden, moving from Eden to S0, cleaning up S1, moving from S1 to S0

5. …

6. When Survivor space objects reach a threshold (survived, let’s say, 8 times) – objects are moved to Tenured generation.

7. Eventually, a **major GC **will be performed on the old generation which cleans up and compacts that space.

-Xms – initial heap size

-Xmx – maximum heap size

-Xmn – size of the Young generation

-XX:PermSize – initial size of the Permanent Generation

-XX: MaxPermSize – maximum size of the Permanent Generation

Concurrent Mark Sweep (CMS) Collector – collects tenured generation concurrently. Normally, this low paused collector does not touch the live objects. If fragmentation becomes a problem, allocate a larger heap.

During each major collection cycle, the CMS collector pauses all the application threads for a brief period at the beginning of the collection and again toward the middle of the collection

[Minor collections can interleave with an ongoing major cycle, and are done in a manner similar to the parallel collector (in particular, the application threads are stopped during minor collections).]()

CMS uses threads to complete the collection of the tenured generation before it becomes full. So the collector threads run in parallel with other application threads. However, if it the collection is not finished before the generation is full, it is **concurrent mode failure **and the entire application is paused.

The garbage First (G1) garbage collector – a long term replacement for CMS collector.