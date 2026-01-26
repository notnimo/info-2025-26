package exercise2;

public class Dog extends Quadruped{
  public Dog(String name){
    super(name);
  }
  
  @Override
  public String speak(){
    return "Woof!";
  }
}