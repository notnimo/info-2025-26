package exercise12;

public class Troll extends Fighter implements EvilFighter {
  public Troll(int attack, int defense) {
    super(attack, defense, EvilFighter.isEvil);
  }
}