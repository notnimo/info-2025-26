package libServer.core;

public class Book {
  private final String title;
  private final Author author;
  private final Publisher publisher;
  private final ISBN isbn;
  private final int year;

  public Book(String title, Author author, Publisher publisher, ISBN isbn, int year) {
    this.title = title;
    this.author = author;
    this.publisher = publisher;
    this.isbn = isbn;
    this.year = year;
  }

  @Override
  public String toString() {
    return isbn + " - " + title + " - " + author + " - " + publisher + " - " + year;
  }

  public boolean doesAuthorMatch(Author author) {
    return this.author.equals(author);
  }

  public boolean doesPublisherMatch(Publisher publisher) {
    return this.publisher.equals(publisher);
  }

  public boolean doesISBNMatch(ISBN isbn) {
    return this.isbn.equals(isbn);
  }

  public boolean doesTitleMatch(String title) {
    return this.title.toLowerCase().contains(title.toLowerCase());
  }

  public boolean doesYearMatch(int year) {
    return this.year == year;
  }
}