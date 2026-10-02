package libServer.core;

public class Publisher {
  private final String name;

  public Publisher(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return name;
  }

  @Override
  public boolean equals(Object obj) {
    if (this.getClass() != obj.getClass()) {
      return false;
    }
    Publisher other = (Publisher) obj;
    return this.name.toLowerCase().contains(other.toString().toLowerCase());
  }
}