package exercise11;

public class Player {
  private String name;
  private Grimoire grimoire;
  private int healthPoints;

  public Player(String name){
    this.name = name;
    this.grimoire = new Grimoire(name);
    this.healthPoints = 100;
  }

  private void checkHP() throws GameException {
    if(this.healthPoints <= 0)  throw new GameException(this.name + "'s HP is 0. Game over");
  }

  public void inflictDamage(int damage) throws GameException {
    System.out.println(this.name + " takes " + damage + " damage");
    this.healthPoints -= damage;
    checkHP();
  }

  public Monster play() throws GameException {
    checkHP();
    GrimoireElement element = this.grimoire.draw();
    if(element instanceof Monster){
      Monster monster = (Monster) element;
      System.out.println(this.name + " has drawn a monster: " + monster.attack);
      return monster;
    } else if(element instanceof Potion){
      this.healthPoints += ((Potion) element).value;
      System.out.println(this.name + " has drawn a potion. New HP: " + this.healthPoints);
      return null;
    }
    return null;
  }

}