package exercise12;

import java.util.Random;

public class Human extends Fighter implements GoodFighter {
  public final static boolean isEvil = false;

  Random rand = new Random();

  public Human() {
    int x = rand.nextInt(50) + 1;
    super(x, 20, GoodFighter.isEvil);
  }
}
