public class Server {
  private final Library library;
  private final int port = 1234;
  private ServerSocket serverSocket;

  public Server(Library library) {
    this.library = library;
  }

  public void stop() throws IOException {
    if (serverSocket != null && !serverSocket.isClosed()) {
      serverSocket.close();
    }

  }

  public void start() throws IOException {
    serverSocket = new ServerSocket(port);
    while(true){
      Socket s = serverSocket.accept();
      ClientHandler clientHandler = new ClientHandler(s, library);
      clientHandler.start();
    }
    
  }
}