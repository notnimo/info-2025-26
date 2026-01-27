package exercise9;

public class Main {
  public static void haveFun(SomeoneThatKnowsHowToHaveFun obj){
    obj.haveFun();
  }
   
  public static void speak(Speaker obj){
    System.out.println(obj.speak());
  }
      
  public static void print(Printable obj){
    System.out.println(obj.print());
  }
  
  public static void main(String[] args){
    Cat tom = new Cat("Tom");
    Dog spike = new Dog("Spike");
    
    Professor jones = new Professor("Jones");
    Student jesse = new Student("Jesse");
    
    haveFun(tom);
    haveFun(spike);
    haveFun(jones);
    haveFun(jesse);
    
    print(tom);
    print(spike);
    print(jones);
    print(jesse);
    
    speak(tom);
    speak(spike);
    speak(jones);
    speak(jesse);
  }
}
