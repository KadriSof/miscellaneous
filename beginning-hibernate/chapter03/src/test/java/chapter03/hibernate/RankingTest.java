package chapter03.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.IntSummaryStatistics;
import java.util.stream.Collectors;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

public class RankingTest {
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
    public void testSaveRanking() {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();

            Person subject = savePerson(session, "Clark Kent");
            Person observer = savePerson(session, "Barry Alen");

            Skill skill = saveSkill(session, "Java");

            Ranking ranking = new Ranking();
            ranking.setSubject(subject);
            ranking.setObserver(observer);
            ranking.setSkill(skill);
            ranking.setRanking(8);
            session.save(ranking);

            tx.commit();
        }
    }

    @Test
    public void testRanking() {
        populateRankingData();

        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();

            Query<Ranking> query = session.createQuery(
                    "from Ranking r "
                    + "where r.subject.name = :name "
                    + "and r.skill.name =: skill", Ranking.class
            );

            query.setParameter("name", "Clark Kent");
            query.setParameter("skill", "Java");

            IntSummaryStatistics stats = query.list()
                    .stream()
                    .collect(
                            Collectors.summarizingInt(Ranking::getRanking)
                    );

            long count = stats.getCount();
            int average = (int) stats.getAverage();

            tx.commit();
            session.close();
            assertEquals(count, 3);
            assertEquals(average, 7);
        }
    }

    @Test
    public void changeRanking() {
        populateRankingData();

        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();

            Query<Ranking> query = session.createQuery(
                    "from Ranking r "
                    + "where r.subject.name = :subject and "
                    + "r.observer.name = :observer and "
                    + "r.skill.name = :skill", Ranking.class
            );

            query.setParameter("subject", "Clark Kent");
            query.setParameter("observer", "Bruce Wayne");
            query.setParameter("skill", "Java");

            Ranking ranking = query.uniqueResult();
            assertNotNull(ranking, "Could not find matching ranking.");
            ranking.setRanking(9); // change the ranking given by Batman to Superman from 6 to 9.

            tx.commit();
            // Hibernate detects the changing made to the ranking model and updated the db when transaction is commited.
            // Kudos to the authors of Hibernate for their amazing work!
        }

        // Now calculate verify if the average is 8 ((7 + 8 + 9) / 3).
        assertEquals(getAverage("Clark Kent", "Java"), 8);
    }

    private void populateRankingData() {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            createData(session, "Clark Kent", "Bruce Wayne", "Java", 6);
            createData(session, "Clark Kent", "Barry Alen", "Java", 7);
            createData(session, "Clark Kent", "Hal Jordan", "Java", 8);
            tx.commit();
        }
    }

    private void createData(Session session,
                            String subjectName,
                            String observerName,
                            String skillName,
                            int rank) {

        Person subject = savePerson(session, subjectName);
        Person observer = savePerson(session, observerName);
        Skill skill = saveSkill(session, skillName);

        Ranking ranking = new Ranking();
        ranking.setSubject(subject);
        ranking.setObserver(observer);
        ranking.setSkill(skill);
        ranking.setRanking(rank);
        session.save(ranking);
    }

    private Skill saveSkill(Session session, String skill) {
        return null;
    }

    private Person findPerson(Session session, String name) {
        Query<Person> query =
                session.createQuery("from Person p where p.name = :name", Person.class);
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

    private int getAverage(String subject, String skill) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();

            Query<Ranking> query = session.createQuery(
                    "from Ranking r "
                    + "where r.subject.name = :name "
                    + "and r.skill.name =: skill", Ranking.class
            );

            query.setParameter("name", subject);
            query.setParameter("skill", skill);

            IntSummaryStatistics stats = query.list()
                    .stream()
                    .collect(
                            Collectors.summarizingInt(Ranking::getRanking)
                    );

            int average = (int) stats.getAverage();
            tx.commit();

            return average;
        }
    }
}
