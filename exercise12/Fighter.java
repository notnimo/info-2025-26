package exercise12;

public abstract class Fighter {
  private int healthPoints = 100;
  private int exp = 0;
  private int attack;
  private int defense;
  private boolean isEvil;

  public Fighter(int attack, int defense, boolean isEvil) {
    this.attack = attack;
    this.defense = defense;
    this.isEvil = isEvil;
  }

  public void increaseExp() {
    this.exp += 1;
  }

  public int getHealthPoints() {
    return this.healthPoints;
  }

  public int getExp() {
    return this.exp;
  }

  public int getAttack() {
    return this.attack;
  }

  public int getDefense() {
    return this.defense;
  }

  public void inflictDamage(int damage) {
    this.healthPoints -= damage;
  }

  public boolean isDead(){
    return this.healthPoints <= 0;
  }

  private boolean checkSide(Fighter opponent) {
    return this.isEvil == opponent.isEvil;
  }

  public Fighter attack(Fighter opponent) throws GameException {

    if(checkSide(opponent)) throw new GameException("two fighters of the same side cannot attack each other");

    int damage = this.getAttack() * this.getExp() - opponent.getDefense() + opponent.getExp();
    
    if(damage > 0) {opponent.inflictDamage(damage);}else {opponent.inflictDamage(0);}

    if(opponent.isDead()) {
      this.increaseExp();
      return opponent;
    } else if(this.isDead()) {
      opponent.increaseExp();
      return this;
    } else {
      opponent.increaseExp();
      this.increaseExp();
      return null;
    }

  }
}