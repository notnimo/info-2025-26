package exercise3;

public class Main {
  public static void main(String[] args) {
    AerospaceObj aerospaceObj = new AerospaceObj("A100", 5000);

    AerospaceObj.Vector vector = aerospaceObj.new Vector("V200", 3000, 4, 15.5);
    AerospaceObj.Satellite satellite = aerospaceObj.new Satellite("S300", 2000, 2, 3);
    AerospaceObj.SpaceStation spaceStation = aerospaceObj.new SpaceStation("SS400", 10000);

    spaceStation.setAstronautsNum(6);

    String[] astronautsNames = {"Alice", "Bob", "Charlie", "David", "Eve", "Frank", "Grace"};
    spaceStation.setAstronautsNum(astronautsNames);

    System.out.println(aerospaceObj.toString());
    System.out.println(vector.toString());
    System.out.println(satellite.toString());
    System.out.println(spaceStation.toString());
  }
}
