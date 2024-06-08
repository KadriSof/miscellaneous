package chapter02.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertEquals;

public class PersistenceTest {

    private SessionFactory sessionFactory = null;

    @BeforeClass
    public void setUp() {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure()
                //.configure("my-special-hibernate.cfg.xml") : custom configuration.
                .build();

        sessionFactory = new MetadataSources(registry).
                buildMetadata()
                .buildSessionFactory();
    }

    public Message saveMessage(String text) {
        Message message = new Message(text);
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            session.persist(message);
            tx.commit();
        }

        return message;
    }

    @Test
    public void readMessage() {
        Message savedMessage = saveMessage("Hello, World!");
        List<Message> list;
        try (Session session = sessionFactory.openSession()) {
            list = session.createQuery("from Message", Message.class).list();
        }
        assertEquals(list.size(), 1);
        list.forEach(System.out::println);
        assertEquals(list.get(0), savedMessage);
    }
}
