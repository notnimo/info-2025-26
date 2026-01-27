package exercise9;

public class Animal implements Speaker, Printable, SomeoneThatKnowsHowToHaveFun {
  public String name;
  
  public String speak(){
    return "Not Defined";
  }

  public String print(){
    return this.name;
  }

  public void haveFun(){};

  public Animal(String name){
    this.name = name;
  }
}
