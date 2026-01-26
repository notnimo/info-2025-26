package exercise7;

public class Arithmetic {
  public static int sum(int a, int b){
    long sum = a + b;
    check(sum);
    return (int) sum;
  }

  public static int multiply(int a, int b){
    long mult = a * b;
    check(mult);
    return (int) mult;
  }

  public static double divide(int a, int b){
    if (b == 0) throw new IllegalArgumentException();
    double res = (double) a / b;
    check((int) res);
    return res;
  }

  private static void check(long a){
    if(a > Integer.MAX_VALUE || a < Integer.MIN_VALUE) throw new ArithmeticException();
  }
}
