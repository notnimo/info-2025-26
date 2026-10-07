public class Server {
  private final Library library;
  private final int port = 1234;
  private ServerSocket serverSocket;

  public Server(Library library) {
    this.library = library;
  }
}