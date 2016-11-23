
public class Fenwick {
  private int [] tree;
  int n;
  
  public Fenwick(int size) {
    n = size;
    tree = new int[n + 1];
  }
  
  public Fenwick(int [] a) {
    this(a.length);
    
    for (int i = 1; i <= n; i++)
      update(i, a[i - 1]);
  }
  
  public int sum(int x) {
    int sum = 0;
    while (x > 0) {
      sum += tree[x];
      x -= (x & -x);
    }
    return sum;
  }
  
  public int sum(int from, int to) {
    return sum(to) - sum(from - 1);
  }
  
  public void update(int x, int val) {
    while (x <= n) {
      tree[x] += val;
      x += (x & -x);
    }
  }
  
  public static void main (String [] args) {
    int [] a = new int []{1, 2, 3, 4, 5, 6};
    Fenwick f = new Fenwick(a);
    System.out.println(f.sum(1, 3));
    f.update(2, 3);
    System.out.println(f.sum(1, 3));
    System.out.println(f.sum(1, 6));
  }
}
