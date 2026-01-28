package exercise12;

import java.util.ArrayList;
import java.util.Random;

public class Main {
  public static void main(String[] args) {

    ArrayList<GoodFighter> goodArmy = new ArrayList<GoodFighter>();
    ArrayList<EvilFighter> evilArmy = new ArrayList<EvilFighter>();
    FighterCreator fc = new FighterCreator();
    Random rand = new Random();

    System.out.println("---------------------------");
    System.out.println("Pascal's battle simulator");
    System.out.println("---------------------------");
    System.out.println("generating armies...");


    for(int i = 0; i < 50; i++){
      try{
        goodArmy.add((GoodFighter) fc.createFighter(rand.nextInt(0,2)));
        evilArmy.add((EvilFighter) fc.createFighter(rand.nextInt(2,4)));
      } catch (Exception e){
        System.out.println(e.getMessage());
      }
    }

    System.out.println("armies generated!");
    //System.out.println("good army: " + goodArmy.toString());
    //System.out.println("evil army: " + evilArmy.toString());
    System.out.println("the battle begins!");

    while(goodArmy.size() > 0 && evilArmy.size() > 0){
      try{
        Fighter goodFighter = (Fighter) goodArmy.get(rand.nextInt(0, goodArmy.size()));
        Fighter evilFighter = (Fighter) evilArmy.get(rand.nextInt(0, evilArmy.size()));

        Fighter defeated = goodFighter.attack(evilFighter);
        if(defeated != null){
          if(defeated instanceof EvilFighter){
            evilArmy.remove((EvilFighter) defeated);
            System.out.println("an evil fighter has been defeated! remaining evil fighters: " + evilArmy.size());
            try{Thread.sleep(250);} catch (InterruptedException e){System.out.println(e.getMessage());}
          } else {
            goodArmy.remove((GoodFighter) defeated);
            System.out.println("a good fighter has been defeated! remaining good fighters: " + goodArmy.size());
            try{Thread.sleep(250);} catch (InterruptedException e){System.out.println(e.getMessage());}
          }
        }
      } catch (GameException e){
        System.out.println(e.getMessage());
      }
      try{
        Fighter goodFighter = (Fighter) goodArmy.get(rand.nextInt(0, goodArmy.size()));
        Fighter evilFighter = (Fighter) evilArmy.get(rand.nextInt(0, evilArmy.size()));

        Fighter defeated = evilFighter.attack(goodFighter);
        if(defeated != null){
          if(defeated instanceof EvilFighter){
            evilArmy.remove((EvilFighter) defeated);
            System.out.println("an evil fighter has been defeated! remaining evil fighters: " + evilArmy.size());
            try{Thread.sleep(250);} catch (InterruptedException e){System.out.println(e.getMessage());}
          } else {
            goodArmy.remove((GoodFighter) defeated);
            System.out.println("a good fighter has been defeated! remaining good fighters: " + goodArmy.size());
            try{Thread.sleep(250);} catch (InterruptedException e){System.out.println(e.getMessage());}
          }
        }
      } catch (GameException e){
        System.out.println(e.getMessage());
      }
    }

    if(goodArmy.size() == 0) {
      System.out.println("the evil army has won the battle!");
    } else {
      System.out.println("the good army has won the battle!");
    }
  }
}
