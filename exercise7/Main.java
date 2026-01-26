package exercise7;

public class Main {
  public static void main(String[] args) {
    Arithmetic.sum(12, 30);
    Arithmetic.sum(12, -30);
    Arithmetic.multiply(2, 23);
    Arithmetic.divide(6, 3);

    //Arithmetic.sum(Integer.MAX_VALUE, 2);
    //Arithmetic.sum(Integer.MIN_VALUE, -2);
    try {
      Arithmetic.multiply(Integer.MAX_VALUE, 2);
    } catch (ArithmeticException exception) {
      System.out.println("exception caught");
    }
    try {
      Arithmetic.divide(13, 0);
    } catch (IllegalArgumentException exception) {
      System.out.println("exception caught");
    } finally {
      System.out.println("finally always executes");
    }
  }
}
