package exercise2;

public class Main {
  public static void main(String[] args) {
    Dog dog = new Dog("Buddy");
    Cat cat = new Cat("Whiskers");

    System.out.println(dog.name + ": " + dog.speak());
    System.out.println(cat.name + ": " + cat.speak());
  }
}
