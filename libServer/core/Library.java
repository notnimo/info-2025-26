package libServer.core;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;

public class Library {
  private final ArrayList<Book> books;

  private void loadDataset() throws IOException {
    Path dataset = null;
    Path[] datasetLocations = {
      Paths.get("books.csv"),
      Paths.get("libServer", "core", "books.csv"),
      Paths.get("core", "books.csv")
    };
    for (Path location : datasetLocations) {
      if (Files.isRegularFile(location)) {
        dataset = location;
        break;
      }
    }
    if (dataset == null) {
      throw new FileNotFoundException("Could not find books.csv. Searched: " + datasetLocations[0]
          + ", " + datasetLocations[1] + ", " + datasetLocations[2]);
    }

    String COMMA_DELIMITER = ",";
    List<List<String>> records = new ArrayList<>();
    String line;

    try (BufferedReader br = new BufferedReader(new FileReader(dataset.toFile()))) {
      while ((line = br.readLine()) != null) {
        String[] values = line.split(COMMA_DELIMITER);
        records.add(Arrays.asList(values));
      }
    }

    ListIterator<List<String>> recordsIter = records.listIterator();
    recordsIter.next(); // skip header row
    while (recordsIter.hasNext()) {
      List<String> record = recordsIter.next();
      ISBN isbn = new ISBN(record.get(0));
      String title = record.get(1);
      Author author = new Author(record.get(2));
      int year = Integer.parseInt(record.get(3));
      Publisher publisher = new Publisher(record.get(4));

      Book book = new Book(isbn, title, author, year, publisher);
      books.add(book);
    }

    System.out.println("Loaded " + books.size() + " books from dataset.");
  }

  private String formatResults(List<Book> books) {
    String results = "";
    for (Book book : books) {
      results += book + "\n";
    }
    return results;
  }

  public String getBooksByAuthor(String authorName) {
    ArrayList<Book> matchingBooks = new ArrayList<Book>();
    Author author = new Author(authorName);
    for (Book book : books) {
      if (book.doesAuthorMatch(author)) {
        matchingBooks.add(book);
      }
    }
    return formatResults(matchingBooks);
  }

  public String getBooksByTitle(String title) {
    ArrayList<Book> matchingBooks = new ArrayList<Book>();
    for (Book book : books) {
      if (book.doesTitleMatch(title)) {
        matchingBooks.add(book);
      }
    }
    return formatResults(matchingBooks);
  }

  public String getBooksByPublisher(String publisherName) {
    ArrayList<Book> matchingBooks = new ArrayList<Book>();
    Publisher publisher = new Publisher(publisherName);
    for (Book book : books) {
      if (book.doesPublisherMatch(publisher)) {
        matchingBooks.add(book);
      }
    }
    return formatResults(matchingBooks);
  }

  public String getBooksByISBN(String isbn) {
    ArrayList<Book> matchingBooks = new ArrayList<Book>();
    ISBN bookISBN = new ISBN(isbn);
    for (Book book : books) {
      if (book.doesISBNMatch(bookISBN)) {
        matchingBooks.add(book);
      }
    }
    return formatResults(matchingBooks);
  }

  public String getBooksByYear(int year) {
    ArrayList<Book> matchingBooks = new ArrayList<Book>();
    for (Book book : books) {
      if (book.doesYearMatch(year)) {
        matchingBooks.add(book);
      }
    }
    return formatResults(matchingBooks);
  }

  public Library() throws IOException {
    this.books = new ArrayList<Book>();
    loadDataset();
  }
}