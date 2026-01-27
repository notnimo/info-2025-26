package exercise10;

public class Person implements Comparable<Person> {
  protected String cf;
  
  public Person(String cf){
    this.cf = cf;
  }

  @Override
  public String toString(){
    return this.cf;
  }

  public int compareTo(Person p2){
    return this.cf.compareTo(p2.cf);
  }
}
