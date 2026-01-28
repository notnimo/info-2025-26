package exercise12;

public class Elf extends Fighter implements GoodFighter {
  public Elf(int attack, int defense) {
    super(attack, defense, GoodFighter.isEvil);
  }
}