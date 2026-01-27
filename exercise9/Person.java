package exercise9;

public class Person implements Speaker, Printable, SomeoneThatKnowsHowToHaveFun {
  public String name;

  public String speak(){
    return this.name;
  }

  public String print(){
    return this.name;
  }

  public void haveFun(){};

  public Person(String name){
    this.name = name;
  }
}
