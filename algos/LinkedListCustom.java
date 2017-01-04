import java.util.Iterator;

public class LinkedListCustom<T> implements Iterable<T> {
  private Node first;
  private int size;

  public void addFirst(T data) {
    Node oldFirst = first;
    first = new Node();
    first.data = data;
    first.next = oldFirst;
    size++;
  }

  public T pop() {
    T data = first.data;
    first = first.next;
    size--;
    return data;
  }

  @Override
  public Iterator<T> iterator() {
    return new ListIterator();
  }

  private class Node {
    T data;
    Node next;
  }

  private class ListIterator implements Iterator<T> {

    @Override
    public boolean hasNext() {
      return size > 0;
    }

    @Override
    public T next() {
      T data = first.data;
      first = first.next;
      return data;
    }
  }

  public static void main (String [] args) {
    LinkedListCustom<Integer> l = new LinkedListCustom<Integer>();
    for (int i = 0; i < 10; i++) {
      l.addFirst(i);
    }

    for (int i : l) {
      System.out.println(i);
    }
  }

}
