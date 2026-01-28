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

    if(checkSide(opponent)) throw new GameException(null);  // update message

    int damageReceived = this.attack * this.exp - opponent.defense + opponent.exp;
    
    if(damageReceived > 0) {opponent.inflictDamage(damageReceived);}else {opponent.inflictDamage(0);}

    if(opponent.isDead()) {
      this.exp += 1;
      return this;
    } else if(this.isDead()) {
      opponent.exp += 1;
      return opponent;
    } else {
      opponent.exp += 1;
      this.exp += 1;
      return null;
    }

  }
}