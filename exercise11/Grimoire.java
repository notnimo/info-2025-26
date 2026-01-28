package exercise11;

import java.util.ArrayList;
import java.util.Collections;

public class Grimoire {
  private ArrayList<GrimoireElement> grimoire;
  private String playerName;

  public Grimoire(String playerName) {
    this.playerName = playerName;
    this.grimoire = new ArrayList<GrimoireElement>();

    for(int i = 0; i < 15; i++){
      this.grimoire.add(new Potion());
    }

    for(int i = 0; i < 25; i++){
      this.grimoire.add(new Monster());
    }

    Collections.shuffle(this.grimoire);
  }

  public GrimoireElement draw() throws GameException {
    if(this.grimoire.isEmpty()) throw new GameException(this.playerName + " has finished the grimoire!");

    return this.grimoire.remove(0);
  }
}