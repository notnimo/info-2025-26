package exercise6;

public class Main {
  public static void main(String[] args) {
    Employee e1 = new Employee("john", "doe");
    e1.setPay(35000);
    Employee e2 = new Employee("tom", "smith", 42000);
    Employee e3 = new Employee("mia", "ross", 70000);

    Employee.printSalarySum();
  }
}
