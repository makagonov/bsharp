https://softwareengineeringdaily.com/2016/03/10/cassandra-tim-berglund/

Cassandra - distributed NoSQL database, built for scale, tabular data (no relations, but you still store rows, can select, insert).
Database with amazing write performance
Write perforamnce secret: 
log structured merge tree
writes get batched up in memory for short period time, and then written out sequentially into immutable data files
you never seeking write on disk and chaning something at the same time
Read performance: flexible ad-hoc reads are hard to perform, should put something at the top (like spark)

Inspired both by Amazon Dynamo paper and Google BigTable paper
From dynamo: the way it spreads data - from Dynamo
distributed HashTable, the data-model is key/value, take key, hash it, mod the number of nodes in cluster - that's the node where the writes go

Distributed hashing (consistent hashing)
When you get new computers: want to do it without taking cluter down. To grow cluster: insert node between two nodes;
Cassandra introduced virtual nodes: imagine a ring of 10 nodes;
when add node, 1.5tb / 9 (from all nodes in the cluster), but the whole burder is not placed on one node

Consistent hashing is core of distributed systems like kafka, cassandra

From bigtable:
data model is from bigtable
Cassandra QL - sql like query language
data organized into tables
table is a hash table of hash tables
row key (partition key) - uniquely identifies row
inside row - another hash table

Table is a hash table - partition key and value, the key gets hash, and it decides on what node that partition will live on
What's inside the partition: value in the hash table is another hash table; the inner HT - that can get too big, if you put too many, you might suffer
Hardware failures can occur. Has built in replication, you decide which nodes will store copy of that data. What if I only can write to 1 or 2 computers? Developer decides when write is good (if one node is dead, or two, or etc).
"Tunable consistency"
Tradefoffs: consistency and latency
Consider, repl factor is 3, usually do I need 2 or 1 to succeed. If decide 1 node is enough - latency will be lower.
I need latency, I will give up some consistency
Same for reading: decide which one is the fastest - give result, sacrifice consistency. Or wait for 2 nodes to respond, and only then consider it as successful read - more latency, but more consistency.

If writing with consistency level QUORUM (more than half of the nodes), I need 2 nodes for read and write to agree - this case it's a **strongly consistent database**. But tradeoff is slower writes and reads.

CAP: Partition Tolerance is not tradable, it's true
In reality, what developers are thinking of is Consistency vs Latency rather than Consistency vs Availability

How cassandra can be tuned for different ratios of reads to writes
Compaction: LSMT
If you're editing a log file - probably it's something bad
logs are things we append to, and we don't change them - that's what cassandra operates on
datafile of cassandra is called SS-table (term from BigTable) that is immutable

Compaction: a number of compaction strategies.
What occurs when you write to cassandra:
- query is executed, and sent over to particular port. What node? some node, and from that node it can get the list of all nodes in the cluster
the driver load balances queries, picks the node
- now the node is a coordinator. there is no master node, but the picked node becomes a coordinator
- coordinator looks at the query, decides on which partition key is there, hash it, and mod the number of nodes - this is the nodes it goes to (node 53)
- but we have replication - it also goes to nodes 54 and 55. The node opens connection to those nodes and waits
- client doesn't know what is going on - it is blocked
- nodes that accept the write: put data in memory (MemTable, buffering up writes before putting them to disk). As it writes to the mem table - it also appends to the commit log. As soon as the commit log write succeeds - that's when it reports write is done, and sends Ack to coordinator
- Coordinator makes consistency decision; might wait for more writes to finish - and then it sends the client that the write is done
- at some point in future, when memory will run out, the MemTable data is flushed into SS-table immutable files

Read is actually harder than write:
- driver, coordinator - all the same
- knows what nodes have the data (53, 54, 55)
- all nodes receive read request
- everything I need is in MemTable
- the more complex situation if we need to go to disk
- imagine sequence of ss-tables: go to the newest ss-table - find everything I need, done
- what if found only a couple of columns out of 6: keep on going, looking at ss-tables, until it's done
- compactions matters a lot - trying to make sure that data is all in one place
- bloomfilter - if something is definitely not there, or probably there; in-memory data-structure, saying: it could be here, or it's not here, don't bother looking into it

More about Cassandra from http://docs.datastax.com/en/cassandra/3.0/
- No single point of failure
- Architercture is based on the fact that system and hardware failures can and do occur
- data is distributed among all nodes in the cluster
- nodes exchange information about itself and other nodes using a gossip protocol
- a sequentially written commit log on each node captures write activity to ensure data durability
- Data is then indexed and written to an in-memory structure, called a **memtable**, which resembles a write-back cache
- Each time the memory structure is full, the data is written to disk in an **SSTables** data file
- All writes are automatically partitioned and replicated throughout the cluster
- Cassandra periodically consolidates SSTables using a process called **compaction**, discarding obsolete data marked for deletion with a tombstone
- Tables require a primary key, which is used for partitioning by row
- clients can connect to any node, read and write requests can be sent to any node
- when client connects to a node - that node becomes coordinator for that 
- All data is first written to **commit log** for durability, and after it all is written to SStables, it can be removed
- SSTble (sorted string table) - append only immutable files to which Cassandra writes data from memtable
- To keep the database healthy, Cassandra periodically merges SSTables and discards old data. This process is called compaction
- 

# Consistent hashing
CH allows distribution of data across a cluster **to minimize reorganization when nodes are added or removed**. Paritions data based on partition key

# Virtual nodes
Vnodes distribute data across nodes at a finer granularity than can be easily achieved if calculated tokens are used.
Vnodes allow each node to own a large number of small partition ranges distributed throughout the cluster. Vnodes also use consistent hashing to distribute data but using them doesn't require token generation and assignment.

# How data is updated
if data already existed - that's fine, the latest row will be in memtable
when data is flushed from memtable to SStable, and when compaction happens - it takes the latest record
because compaction happens on nodes independently, it is possible that read will take older version of the row. In this case read can look at multiple results from multiple nodes, and send to the client the latest data
# How data is deleted
Cassandra treats deletion as insert or upsert. deletion marker is added to the row (called tombstone), and they go through the write path and are written to SStables. Tombstones have expiration period, and they get deleted as part of normal compaction process.
Tombstones might fail to reach the replica nodes (we're in distributed system!), so Zombies are possible to be there. 

# When reading
Each SSTable has a bloom-filter associated with it.

Data model
Having extra writes is fine - cassandra is good for it
reads are more expensive
Rule 1. Spread data evenly around the cluster
rows are spread based on the hash of the partition key - first element of the PRIMARY KEY
Pick a good primary key
Rule 2. Minimize the number of partitions read
In order to reduce coordination and movements between nodes

The 2 rules above often conflict, so you need to find a balance.

Data modeling.
Step 1. Determine what queries to support
- grouping by an attribute
- ordering by an attribute
- filtering based on some set of conditions
- enforcing uniqueness
- etc

Step 2. Try to create a table where you can satisfy your query by reading one partition
Each table should pre-build the "answer" to a high-level query that you need to support.
**Data duplication is ok - many tables can repeat your data.**

compound key: (PARTITION_KEY,CLUSTERING_KEY) - partition key defines on which partition row will reside, and clustering key defines the order

