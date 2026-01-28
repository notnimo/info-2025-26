package exercise12;

public class Human extends Fighter implements GoodFighter {
  public Human(int attack, int defense) {
    super(attack, defense, GoodFighter.isEvil);
  }
}
