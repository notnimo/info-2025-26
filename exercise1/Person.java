package exercise1;

public class Person {
  public int age;
  public String name;
  public String surname;
  public int height;

  public Person(int age, String name, String surname, int height) {
    this.age = age;
    this.name = name;
    this.surname = surname;
    this.height = height;
  }

  public void printInfo() {
    System.out.println("Name: " + name + " " + surname);
    System.out.println("Age: " + age);
    System.out.println("Height: " + height + " cm");
    System.out.println("---------------");
  }
}