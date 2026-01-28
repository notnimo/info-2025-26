package exercise11;

import java.util.Random;

public class Potion implements GrimoireElement{
  public int value;

  static public Random random = new Random();

  public Potion(){
    this.value = random.nextInt(1,11);
  }
}