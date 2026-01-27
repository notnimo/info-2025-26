package exercise9;

public class Cat extends Animal {
  
  public String purr(){
    return "purr purr purr";
  }

  @Override
  public void haveFun(){
    System.out.println(purr());
  };

  @Override
  public String speak(){
    return "Meow";
  }

  public Cat(String name){
    super(name);
  }
}
