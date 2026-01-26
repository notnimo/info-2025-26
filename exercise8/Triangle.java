package exercise8;

public class Triangle extends Polygon {
  public Triangle(double[] sides){
    super(sides);
    if(!(sides.length == 3) || !(sides[0] + sides[1] > sides[2] || sides[1] + sides[2] > sides[0] || sides[0] + sides[2] > sides[1])) throw new IllegalArgumentException();
  }

  @Override
  public void draw(){
    System.out.println("Triangle");
  }
}
