package exercise2;

public class Animal{
  protected String name;
  protected int nLegs;

  public Animal(String name, int nLegs){
    this.name = name;
    this.nLegs = nLegs;
  }

  public String speak(){
    return "no call defined";
  }
} 
