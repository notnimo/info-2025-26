package exercise8;

public class Main {
    public static void main(String[] args) {
        GeometricShape shape1 = new GeometricShape();

        Polygon Polygon1 = new Polygon(new double[]{1.0, 2.0, 3.0, 4.5, 6.0});

        try {
            Quadrilateral impossibleQuadrilateral1 = new Quadrilateral(new double[]{1.0, 2.0, 3.0});
        } catch (IllegalArgumentException iae) {
            System.out.println("Exception: " + iae.toString());
        }

        Quadrilateral Quadrilateral1 = new Quadrilateral(new double[]{1.0, 2.0, 1.0, 2.0});

        try {
            Rectangle impossibleRectangle1 = new Rectangle(new double[]{1.0, 2.0, 1.0, 3.0});
        } catch (IllegalArgumentException iae) {
            System.out.println("Exception: " + iae.toString());
        }

        Rectangle Rectangle1 = new Rectangle(new double[]{1.0, 2.0, 1.0, 2.0});

        try {
            Square impossibleSquare1 = new Square(new double[]{1.0, 2.0, 3.0});
        } catch (IllegalArgumentException iae) {
            System.out.println("Exception: " + iae.toString());
        }

        try {
            Square impossibleSquare2 = new Square(new double[]{1.0, 2.0, 1.0, 1.0});
        } catch (IllegalArgumentException iae) {
            System.out.println("Exception: " + iae.toString());
        }

        Square Square1 = new Square(new double[]{1.0f, 1.0f, 1.0f, 1.0f});

        try {
            Triangle impossibleTriangle1 = new Triangle(new double[]{1.0, 2.0, 3.0, 4.0});
        } catch (IllegalArgumentException iae) {
            System.out.println("Exception: " + iae.toString());
        }

        try {
            Triangle impossibleTriangle2 = new Triangle(new double[]{1.0, 2.0, 3.0});
        } catch (IllegalArgumentException iae) {
            System.out.println("Exception: " + iae.toString());
        }

        Triangle Triangle1 = new Triangle(new double[]{1.0f, 2.0f, 2.0f});

        Circle Circle1 = new Circle(5.0);

        shape1.draw();
        Polygon1.draw();
        Quadrilateral1.draw();
        Rectangle1.draw();
        Square1.draw();
        Triangle1.draw();
        Circle1.draw();

        System.out.println("Perimeter del Square: " + Square1.getPerimeter());
        System.out.println("Perimeter del Rectangle: " + Rectangle1.getPerimeter());
        System.out.println("Perimeter del Triangle: " + Triangle1.getPerimeter());
        System.out.println("Circumference del Circle: " + Circle1.getCircumference());
    }

}