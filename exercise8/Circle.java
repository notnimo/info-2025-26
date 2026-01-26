package exercise8;

import java.lang.Math;

public class Circle extends GeometricShape {
  protected double radius;

  @Override
  public void draw(){
    System.out.println("Circle");
  }

  public double getCircumference(){
    return 2*this.radius*Math.PI;
  }

  public Circle(double radius){
    this.radius = radius;
  }
}
