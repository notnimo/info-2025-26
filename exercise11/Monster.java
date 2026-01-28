package exercise11;

import java.util.Random;

public class Monster implements GrimoireElement{
  public int attack;

  static public Random random = new Random();

  public Monster(){
    this.attack = random.nextInt(1,16);
  }
}