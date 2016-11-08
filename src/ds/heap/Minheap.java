package ds.heap;

public class MinHeap {
  Comparable [] heap;
  int heapSize;
  public MinHeap(int maxSize) {
    heap = new Comparable[maxSize + 1];
  }

  public Comparable pop() {
    Comparable ret = heap[1];
    swap(1, heapSize--);
    sinkDown(1);
    return ret;
  }

  public void add(Comparable x) {
    heap[++heapSize] = x;
    swimUp(heapSize);
  }

  public boolean isEmpty() {
    return heapSize == 0;
  }

  private void swap(int pos1, int pos2) {
    Comparable tmp = heap[pos1];
    heap[pos1] = heap[pos2];
    heap[pos2] = tmp;
  }

  private boolean less(int pos1, int pos2) {
    return heap[pos1].compareTo(heap[pos2]) < 0;
  }

  private void swimUp(int pos) {
    if (pos == 1) return;
    int parent = pos / 2;
    if (less(pos, parent)) {
      swap(parent, pos);
      swimUp(parent);
    }
  }

  private void sinkDown(int pos) {
    int left = pos * 2;
    int right = left + 1;
    int minPos = pos;
    if (left <= heapSize && less(left, minPos)) {
      minPos = left;
    }
    if (right <= heapSize && less(right, minPos))
      minPos = right;

    if (minPos != pos) {
      swap(minPos, pos);
      sinkDown(minPos);
    }
  }
}
