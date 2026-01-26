package exercise6;

public class Employee {
  private String name;
  private String surname;
  private double pay;

  protected static double paySum;

  public Employee(String name, String surname){
    this.name = name;
    this.surname = surname;
    this.pay = 0;
  }

  public Employee(String name, String surname, double pay){
    this.name = name;
    this.surname = surname;
    this.pay = pay;
    paySum += pay;
  }

  public String getName(){
    return this.name;
  }

  public String getSurame(){
    return this.surname;
  }

  @Override
  public String toString(){
    return this.name + this.surname;
  }

  protected void setPay(double pay){
    paySum = paySum - this.pay + pay;
    this.pay = pay;
  }

  protected static void printSalarySum(){
    System.out.println("Salary sum: " + Employee.paySum);
  }
}
