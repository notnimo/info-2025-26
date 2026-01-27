package exercise10;

import java.util.Arrays;

public class Main  {
  public static void main(String[] args) {
    Cat[] cats = {new Cat("123456"), new Cat("654321")};
    Person[] people = {new Person("1b2b3b4b"), new Person("1a2a3a4a"), new Person("1c2c3c4c")};

    Arrays.sort(cats);
    for(Cat c: cats){
      System.out.println(c);
    }

    Arrays.sort(people);
    for(Person p: people){
      System.out.println(p);
    }
  }  
}
