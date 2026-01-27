package exercise9;

public class Student extends Person{

  public void goClubbing(){}

  @Override
  public void haveFun(){
    goClubbing();
  }

  @Override
  public String speak(){
    return "Hi, I'm " + this.name +  " and I'm a student";
  }

  public Student(String name){
    super(name);
  }
  
}
