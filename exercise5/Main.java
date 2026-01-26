package exercise5;

public class Main {
  static void printObj(Spaceship s){
    System.out.println("Spaceship is shooting ");
    s.shoot();
  }

  static void printObj(RebelSpaceship r){
    System.out.println("Rebel spaceship is shooting ");
    r.shoot();
  }
  public static void main(String[] args) {
    Spaceship starDestroyer = new Spaceship();
    Spaceship xWing = new RebelSpaceship();

    printObj(starDestroyer);
    printObj(xWing);

    starDestroyer.shoot();
    xWing.shoot();
  }
}
