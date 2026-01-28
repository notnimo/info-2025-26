package exercise11;

import java.util.Random;

public class Main {
  public static void main(String[] args) {
    System.out.println( "Welcome to FPGG - Fantasy Pascal Geeky Game");

    Player player1 = new Player("Alice");
    Player player2 = new Player("Bob");

    Random randomNatEvents = new Random();

    int currentTurn = 0;

    while(true){
      currentTurn++;
      System.out.println("Turn " + currentTurn);
      try {Thread.sleep(750);} catch (InterruptedException e) { e.printStackTrace(); }
      Monster monster1 = null;
      Monster monster2 = null;
      try{
        monster1 = player1.play();
        monster2 = player2.play();

        if(monster1 != null){
          player2.inflictDamage(monster1.attack);
        }else if(monster2 != null){
          player1.inflictDamage(monster2.attack);
        }

        int natEvent = randomNatEvents.nextInt(0, 11);
        if(natEvent == 0 || natEvent == 1){
          Lightning lightning = new Lightning();
          System.out.println("A lightning strikes both players");
          player1.inflictDamage(lightning.damage);
          player2.inflictDamage(lightning.damage);
        } else if(natEvent == 2 || natEvent == 3){
          Earthquake earthquake = new Earthquake();
          System.out.println("An earthquake shakes both players");
          player1.inflictDamage(earthquake.damage);
          player2.inflictDamage(earthquake.damage);
        }
      }catch(GameException e){
        System.out.println("Game over " + e.toString());
        break;
      }
      
    }
  }
}