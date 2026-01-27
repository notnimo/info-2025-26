package exercise9;

public class Dog extends Animal {

  public void catchFrisbee(){}

  @Override
  public void haveFun(){
    catchFrisbee();
  }

  @Override
  public String speak(){
    return "Woof";
  }

  public Dog(String name){
    super(name);
  }
}