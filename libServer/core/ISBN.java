package libServer.core;

public class ISBN {
  private final String code;

  public ISBN(String code) {
    this.code = code;
  }

  @Override
  public String toString() {
    return code;
  }

  @Override
  public boolean equals(Object obj) {
    if (this.getClass() != obj.getClass()) {
      return false;
    }
    ISBN other = (ISBN) obj;
    return this.code.toLowerCase().contains(other.toString().toLowerCase());
  }
}