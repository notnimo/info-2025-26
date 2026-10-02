package libServer.core;

public class Author {
  private final String name;

  public Author(String name) {
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
    Author other = (Author) obj;
    return this.name.toLowerCase().contains(other.toString().toLowerCase());
  }
}