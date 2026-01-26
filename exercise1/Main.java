package exercise1;

public class Main {
  public static void main(String[] args) {
    Person person1 = new Person(25, "John", "Doe", 180);
    Person person2 = new Person(30, "Jane", "Smith", 165);
    Person person3 = new Person(22, "Alice", "Johnson", 170);
    Person person4 = new Person(28, "Bob", "Brown", 175);

    person1.printInfo();
    person2.printInfo();
    person3.printInfo();
    person4.printInfo();
  }
}
