package chapter03.hibernate;

import chapter03.simple.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class PersonTest {
    SessionFactory sessionFactory = null;

    @BeforeClass
    public void setup() throws Exception {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure()
                .build();

        sessionFactory = new MetadataSources(registry)
                .buildMetadata()
                .buildSessionFactory();
    }

    @Test
    public void testSavePerson() throws Exception {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            Person person = new Person();
            person.setName("Bruce Wayne");
            session.save(person);
            tx.commit();
        }
    }

    private Person findPerson(Session session, String name) {
        Query<Person> query = session.createQuery("from Person p where p.name = :name", Person.class);
        query.setParameter("name", name);

        return (Person) query.uniqueResult();
    }

    private Person savePerson(Session session, String name) {
        Person person = findPerson(session, name);
        if (person == null) {
            person = new Person();
            person.setName(name);
            session.save(person);
        }

        return person;
    }
}
