package exercise12;

public class GameException extends Exception{
  public String message;

  public GameException(String message){
    super(message);
    this.message = message;
  }

  @Override
  public String toString(){
    return this.message;
  }
}
