package exercise9;

public class Professor extends Person{

  public String doSomethingExtremelyBoring(){
    return "";
  }

  @Override
  public void haveFun(){
    doSomethingExtremelyBoring();
  }

  @Override
  public String speak(){
    return "Hello there, I'm professor " + this.name;
  }
  
  public Professor(String name){
    super(name);
  }
}
