package exercise8;

public class Polygon extends GeometricShape {
  private double[] sides;

  public Polygon(double[] sides){
    this.sides = sides.clone();
  }

  @Override
  public void draw(){
    System.err.println("Polygon");
  }

  public double getPerimeter(){
    double perimeter = 0;
    for(double side: this.sides){
      perimeter += side;
    }
    return perimeter;
  }
}
