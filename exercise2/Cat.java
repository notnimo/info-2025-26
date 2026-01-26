package exercise2;

public class Cat extends Quadruped{
  public Cat(String name){
    super(name);
  }

  @Override
  public String speak(){
    return "Meow!";
  }
}
