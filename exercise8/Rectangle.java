package exercise8;

public class Rectangle extends Quadrilateral {
  public Rectangle(double sides[]){
    super(sides);
    if(!(sides[0] == sides[1] && sides[2] == sides[3]) && !(sides[0] == sides[2] && sides[1] == sides[3]) && !(sides[0] == sides[3] && sides[1] == sides[2])) throw new IllegalArgumentException();
  }

  @Override
  public void draw(){
    System.out.println("Rectangle");
  }
}
