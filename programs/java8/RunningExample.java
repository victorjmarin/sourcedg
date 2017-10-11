public class RunningExample {

  public void m1(int p1) {
    do {
      p1 = m2(p1);
    } while (p1 > 0);

  }

  public int m2(int p2) {
    if (p2 > 0)
      p2--;
    return p2;
  }

}
