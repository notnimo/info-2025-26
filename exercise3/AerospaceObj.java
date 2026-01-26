package exercise3;

public class AerospaceObj {
  protected String seriesNum;
  protected double weight;

  class Vector extends AerospaceObj{
    protected int propellorsNum;
    protected double height;

    @Override
    public String toString() {
      return "Vector{" +
              "seriesNum='" + seriesNum + '\'' +
              ", weight=" + weight +
              ", propellorsNum=" + propellorsNum +
              ", height=" + height +
              '}';
    }

    public Vector(String seriesNum, double weight, int propellorsNum, double height) {
      super(seriesNum, weight);
      this.propellorsNum = propellorsNum;
      this.height = height;
    }
  }

  class Satellite extends AerospaceObj{
    protected int solarPanelsNum;
    protected int reactionWheelsNum;

    @Override
    public String toString() {
      return "Satellite{" +
              "seriesNum='" + seriesNum + '\'' +
              ", weight=" + weight +
              ", solarPanelsNum=" + solarPanelsNum +
              ", reactionWheelsNum=" + reactionWheelsNum +
              '}';
    }

    public Satellite(String seriesNum, double weight, int solarPanelsNum, int reactionWheelsNum) {
      super(seriesNum, weight);
      this.solarPanelsNum = solarPanelsNum;
      this.reactionWheelsNum = reactionWheelsNum;
    }
  }

  class SpaceStation extends AerospaceObj{
    protected int astronautsNum = 0;

    public int getAstronautsNum() {
      return astronautsNum;
    }

    public void setAstronautsNum(int astronautsNum) {
      this.astronautsNum = astronautsNum;
      System.out.println("Number of astronauts set to: " + astronautsNum);
    }

    public void setAstronautsNum(String[] astronautsNames) {
      this.astronautsNum = astronautsNames.length;
      System.out.println("Number of astronauts set to: " + this.astronautsNum);
    }

    @Override
    public String toString() {
      return "SpaceStation{" +
              "seriesNum='" + seriesNum + '\'' +
              ", weight=" + weight +
              ", astronautsNum=" + astronautsNum +
              '}';
    }

    public SpaceStation(String seriesNum, double weight) {
      super(seriesNum, weight);
    }
  }
  
  @Override
  public String toString() {
    return "AerospaceObj{" +
            "seriesNum='" + seriesNum + '\'' +
            ", weight=" + weight +
            '}';
  }

  public AerospaceObj(String seriesNum, double weight) {
    this.seriesNum = seriesNum;
    this.weight = weight;
  }
}
