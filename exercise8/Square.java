package exercise8;

public class Square extends Quadrilateral {
  public Square(double[] sides){
    super(sides);
    if(!(sides[0] == sides[1] && sides[0] == sides[2] && sides[0] == sides[3])) throw new IllegalArgumentException();
  }

  @Override
  public void draw(){
    System.out.println("Square");
  }
}
