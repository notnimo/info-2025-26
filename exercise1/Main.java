package exercise1;

public class Main {
  public static void main(String[] args) {
    Persona person1 = new Persona(25, "John", "Doe", 180);
    Persona person2 = new Persona(30, "Jane", "Smith", 165);
    Persona person3 = new Persona(22, "Alice", "Johnson", 170);
    Persona person4 = new Persona(28, "Bob", "Brown", 175);

    person1.printInfo();
    person2.printInfo();
    person3.printInfo();
    person4.printInfo();
  }
}