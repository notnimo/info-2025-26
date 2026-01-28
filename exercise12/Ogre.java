package exercise12;

public class Ogre extends Fighter implements EvilFighter {
  public Ogre(int attack, int defense) {
    super(attack, defense, EvilFighter.isEvil);
  }
}
