public class Vector2D251 implements Iterator<Integer> {
  Iterator<Integer> curIterator;
  Iterator<List<Integer>> listsIterator;
  public Vector2D(List<List<Integer>> vec2d) {
    this.listsIterator = vec2d.iterator();
    ensureItemsIterator();
  }

  void ensureItemsIterator() {
    while((curIterator == null || !curIterator.hasNext()) && this.listsIterator.hasNext()) {
      curIterator = listsIterator.next().iterator();
    }
  }

  @Override
  public Integer next() {
    Integer ret = curIterator.next();
    ensureItemsIterator();
    return ret;
  }

  @Override
  public boolean hasNext() {
    return curIterator != null && curIterator.hasNext();
  }
}
