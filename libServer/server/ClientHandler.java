package libServer.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import libServer.core.Library;

public class ClientHandler extends Thread {
  private final Socket socket;
  private final Library library;
  private final PrintWriter writer;
  private final BufferedReader reader;

  public ClientHandler(Socket socket, Library library) throws IOException {
    this.socket = socket;
    this.library = library;
    this.writer = new PrintWriter(socket.getOutputStream(), true);
    this.reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
  }

  private String handleRequest(String request){
    RequestID requestid = RequestID.values()[Integer.parseInt(request.substring(0, 1))];
    String requestData = request.substring(4, request.length());
    String response = "";

    switch (requestid) {
      case AUTHOR:
        response = library.getBooksByAuthor(requestData);
        break;
      case TITLE:
        response = library.getBooksByTitle(requestData);
        break;
      case PUBLISHER:
        response = library.getBooksByPublisher(requestData);
        break;
      case ISBN:
        response = library.getBooksByISBN(requestData);
        break;
      case YEAR:
        response = library.getBooksByYear(Integer.parseInt(requestData));
        break;
      default:
        response = "Invalid request ID.";
    }

    return response;
  }

  @Override
  public void run() {
    try{
      String request;
      while ((request = reader.readLine()) != null) {
        System.out.println("[ClientHandler " + socket + "]");
        System.out.println(" [RX] " + request);

        String response = handleRequest(request);
        System.out.println("[ClientHandler " + socket + "]");
        System.out.println(" [TX] " + response);

        writer.println(response + "@@@");

        if(request.contains("bye")){
          System.out.println("[ClientHandler " + socket + "]");
          System.out.println(" Socket closing");
          break;
        }
      }

      reader.close();
      writer.close();
      socket.close();
    } catch (IOException e) {
      System.err.println("[ClientHandler " + socket + "]");
      System.err.println(" Error: " + e.getMessage());
    }
  }
}