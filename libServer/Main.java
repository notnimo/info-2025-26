package libServer;

import java.io.IOException;

import libServer.core.Library;
import libServer.server.Server;

public class Main {
  public static void main(String[] args) throws IOException {
    Library library = new Library();
    String results;

    System.out.println("----------------------------------\n");
    results = library.getBooksByAuthor("Gaiman");
    System.out.println("Looking for books written by Gaiman\n");
    System.out.print(results);
    System.out.println("----------------------------------\n");
    results = library.getBooksByTitle("Android");
    System.out.println("Looking for books containing the word Android in the title\n");
    System.out.print(results);
    System.out.println("----------------------------------\n");
    results = library.getBooksByPublisher("O'Reilly");
    System.out.println("Looking for books published by O'Reilly\n");
    System.out.print(results);
    System.out.println("----------------------------------\n");
    results = library.getBooksByYear(1986);
    System.out.println("Looking for books published in year 1986\n");
    System.out.print(results);
    System.out.println("----------------------------------\n");
    results = library.getBooksByISBN("140431179");
    System.out.println("Looking for book with a given ISBN code\n");
    System.out.print(results);
    System.out.println("----------------------------------\n");
    Server server = new Server(library);
    server.start();
  }
}