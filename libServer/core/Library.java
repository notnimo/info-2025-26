package libServer.core;

import java.util.ArrayList;

import libServer.core.Books;

public class Library {
  private final ArrayList<Book> books;

  private void loadDataset() throws FileNotFoundException {
    String COMMA_DELIMITER = ",";
    List<List<String>> records = new ArrayList<>();
    BufferedReader br = new BufferedReader(new FileReader("./../books.csv"));
    String line;

    while ((line = br.readLine()) != null) {
      String[] values = line.split(COMMA_DELIMITER);
      records.add(Arrays.asList(values));
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

  public Library() throws FileNotFoundException {
    this.books = new ArrayList<Book>();
    loadDataset();
  }
}