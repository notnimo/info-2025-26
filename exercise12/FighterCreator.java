package exercise12;

import java.util.Random;

public class FighterCreator {

  private static Random rand = new Random();

  public Fighter createFighter(int type) throws GameException {
    if(type == Classes.HUMAN.ordinal()) return new Human(rand.nextInt(21 , 51), rand.nextInt(30 , 51));
    else if (type == Classes.ELF.ordinal()) return new Elf(rand.nextInt(28 , 51), rand.nextInt(28 , 51));
    else if(type == Classes.OGRE.ordinal()) return new Ogre(rand.nextInt(37 , 51), rand.nextInt(11, 51));
    else if (type == Classes.TROLL.ordinal()) return new Troll(rand.nextInt(32, 51), rand.nextInt(23 , 51));
    throw new GameException("Unknown fighter type: " + type);
  }
}
