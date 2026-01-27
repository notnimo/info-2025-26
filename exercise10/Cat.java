package exercise10;

public class Cat implements Comparable<Cat>{
  protected String microchip;

  public Cat(String microchip) {
    this.microchip = microchip;
  }

  @Override
  public String toString(){
    return this.microchip;
  }

  @Override
  public int compareTo(Cat c2){
    return this.microchip.compareTo(c2.microchip);
  }
}
