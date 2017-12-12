
public class Paper {

  double t0p() {
    double s = 0;
    for (int i = 0; i < D.length; i++)
      s += D[1][i] - (th1 * D[0][i] + th0);
    return -2 * i / D.length;
  }


}
