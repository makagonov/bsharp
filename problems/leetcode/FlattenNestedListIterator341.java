public class FlattenNestedListIterator341 implements Iterator<Integer> {
  Iterator<NestedInteger> curIterator;
  Iterator<Integer> childIterator = null;
  Integer next = null;
  public NestedIterator(List<NestedInteger> nestedList) {
    curIterator = nestedList.iterator();
    this.next = this.next();
  }

  @Override
  public Integer next() {
    if (this.next != null) {
      Integer ret = this.next;
      this.next = null;
      this.next = this.next();
      return ret;
    }
    while (childIterator == null || !childIterator.hasNext()) {
      NestedInteger next = curIterator.hasNext() ? curIterator.next() : null;
      if (next == null) return null;
      if (next.isInteger()) return next.getInteger();
      childIterator = new NestedIterator(next.getList());
    }
    return childIterator.next();
  }

  @Override
  public boolean hasNext() {
    return this.next != null;
  }
}

