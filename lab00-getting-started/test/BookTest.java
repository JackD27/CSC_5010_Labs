import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import person.Book;
import person.Person;

/**
 * A JUnit test class for the Book class.
 */
public class BookTest {

  private Book book;
  private Person author;

  /**
   * This BookTest setup creates an author then book and adds the author to the book.
   */

  @Before
  public void setUp() {

    author = new Person("book", "Doe", 1945);
    book = new Book("Diary of a Wimpy Kid", author, 19.99f);
  }

  @Test
  public void testTitle() {
    assertEquals("Diary of a Wimpy Kid", book.getTitle());
  }

  @Test
  public void testPrice() {
    assertEquals(19.99f, book.getPrice(), 0.00);
  }

  @Test
  public void testAuthor() {
    assertEquals(author, book.getAuthor());
  }

}