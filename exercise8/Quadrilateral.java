package exercise8;

public class Quadrilateral extends Polygon{
  public Quadrilateral(double[] sides){
    super(sides);
    if(sides.length != 4) throw new IllegalArgumentException();
  }

  @Override
  public void draw(){
    System.out.println("Quadrilateral");
  }
}
