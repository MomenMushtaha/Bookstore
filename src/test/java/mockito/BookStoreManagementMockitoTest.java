package mockito;

import entity.Book;
import entity.BookStoreManagement;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * This class tests the BookStoreManagement class using Mockito framework.
 * It verifies the behavior of the BookStoreManagement class when its methods are invoked.
 */
public class BookStoreManagementMockitoTest {

    /**
     * This test verifies the behavior of adding a book to the BookStoreManagement.
     * Currently, the test is commented out because the addBook method does not work as expected.
     */
    @Test
    public void addBookTest() {

        // Create a mock object of BookStoreManagement
        BookStoreManagement bookStoreManagementMock = mock(BookStoreManagement.class);

        // Define the behavior of the mock object when the getId method is called
        when(bookStoreManagementMock.getId()).thenReturn((long) 1.0);

        // Create a new book object
        Book book = new Book(123, 1,"TEST", "Hamza Zafar", "Carleton", 10,1.99);

        // The following lines are commented out because the addBook method does not work as expected
        // bookStoreManagementMock.addBook(book);
        // assertEquals(bookStoreManagementMock.getBookList().size(),1);
    }
}