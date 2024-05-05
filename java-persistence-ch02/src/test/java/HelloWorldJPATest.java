import com.msk.Message;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertAll;

public class HelloWorldJPATest {

    @Test
    public void storeLoadMessage() {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ch02");

        try {
            EntityManager em = emf.createEntityManager();

            em.getTransaction().begin(); // start transaction

            Message message = new Message();
            message.setText("Hello!");

            em.persist(message); // persist data

            em.getTransaction().commit();
            // INSERT into MESSAGE (ID, TEXT) values (1, 'Hello!')

            em.getTransaction().begin(); // start a second transaction

            List<Message> messages =
                    em.createQuery("select m from Message m", Message.class)
                            .getResultList();

            // SELECT * From MESSAGE

            messages.get(messages.size() - 1).setText("Hello World from JPA!");

            em.getTransaction().commit();
            // UPDATE MESSAGE set TEXT = 'Hello World from JPA!' where ID = 1

            assertAll(
                    () -> assertEquals(1, messages.size()),
                    () -> assertEquals("Hello World from JPA!", messages.get(0).getText())
            );

            em.close();

        } finally {
            emf.close();
        }
    }
}
