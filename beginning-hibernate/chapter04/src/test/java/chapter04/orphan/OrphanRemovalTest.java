package chapter04.orphan;

import com.msk.hibernate.util.SessionUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.*;

public class OrphanRemovalTest {

    @Test
    public void orphanRemovalTest() {
        Long id = createLibrary();

        try (Session session = SessionUtil.getSession()) {
            Transaction tx = session.beginTransaction();

            // create a persistent library obj,
            // and assign to it the already created library.
            Library library = session.load(Library.class, id);
            assertEquals(library.getBooks().size(), 3);

            // remove one book from the library books' list,
            // which will make it an orphan (a book without a library).
            library.getBooks().remove(0);
            assertEquals(library.getBooks().size(), 2);

            tx.commit();
        }

        try (Session session = SessionUtil.getSession()) {
            Transaction tx = session.beginTransaction();

            // create a second persistent library obj,
            // and assign to it the already created library.
            Library l2 = session.load(Library.class, id);
            assertEquals(l2.getBooks().size(), 2);

            // query all the books from the Books table.
            Query<Book> query = session
                    .createQuery("from Book b", Book.class);

            // verify the number of books to see if the orphan was deleted.
            List<Book> books = query.list();
            assertEquals(books.size(), 2);

            tx.commit();
        }
    }

    @Test
    public void deleteLibrary() {
        Long id = createLibrary();

        try (Session session = SessionUtil.getSession()) {
            Transaction tx = session.beginTransaction();

            // delete the whole library from the database.
            Library library = session.load(Library.class, id);
            assertEquals(library.getBooks().size(), 3);
            session.delete(library);

            tx.commit();
        }

        try (Session session = SessionUtil.getSession()) {
            Transaction tx = session.beginTransaction();

            // test whether the books attached to the deleted libraries were remove
            // since all the books became orphans.
            Library library = session.get(Library.class, id);
            // don't use session.load()!!
            // unless you cast the returned value to a 'Library' obj type.
            assertNull(library);

            List<Book> books = session
                    .createQuery("from Book b", Book.class).list();

            assertEquals(books.size(), 0);
        }
    }

    private Long createLibrary() {
        Library library = null;

        try (Session session = SessionUtil.getSession()) {
            Transaction tx = session.beginTransaction();

            library = new Library();
            library.setName("orphanLib");
            session.save(library);

            Book book = new Book();
            book.setLibrary(library);
            book.setTitle("Meditations");
            session.save(book);
            library.getBooks().add(book);

            book = new Book();
            book.setLibrary(library);
            book.setTitle("Don Quixote");
            session.save(book);
            library.getBooks().add(book);

            book = new Book();
            book.setLibrary(library);
            book.setTitle("Norwegian Wood");
            session.save(book);
            library.getBooks().add(book);

            tx.commit();
        }

        return library.getId();
    }
}
