package chapter03.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;
import org.testng.annotations.AfterMethod;
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

    @AfterMethod
    public void shutdown() {
        sessionFactory.close();
    }

    //tag::testSaveRanking[]
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
    //end::testSaveRanking[]

    //tag:testRankings[]
    @Test
    public void testRankings() {
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
    //end::testRankings[]

    //tag::changeRanking[]
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
    //end::changeRanking[]

    //tag::removeRanking[]
    @Test
    public void removeRanking() {
        populateRankingData();
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            Ranking ranking = findRanking(session, "Clark Kent", "Bruce Wayne", "Java");
            assertNotNull(ranking, "Ranking not found.");
            session.delete(ranking);
            tx.commit();
            assertEquals(getAverage("Clark Kent", "Java"), 7);
        }
    }
    //end::removeRanking[]

    //tag::findRanking[]
    private Ranking findRanking(Session session, String subject, String observer, String skill) {
        Query<Ranking> query = session.createQuery(
                "from Ranking r "
                        + "where r.subject.name = :subject and "
                        + "r.observer.name = :observer and "
                        + "r.skill.name = :skill", Ranking.class
        );
        query.setParameter("subject", subject);
        query.setParameter("observer", observer);
        query.setParameter("skill", skill);

        return query.uniqueResult();
    }
    //end::findRanking[]

    //tag::populateRankingData[]
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
    //end::populateRankingData[]

    private Skill findSkill(Session session, String name) {
        Query<Skill> query = session.createQuery(
                "from Skill s where s.name = :name", Skill.class
        );
        query.setParameter("name", name);

        return query.uniqueResult();
    }

    private Skill saveSkill(Session session, String skillName) {
        Skill skill = findSkill(session, skillName);
        if (skill == null) {
            skill = new Skill();
            skill.setName(skillName);
            session.save(skill);
        }

        return skill;
    }

    //tag::findPerson[]
    private Person findPerson(Session session, String name) {
        Query<Person> query =
                session.createQuery("from Person p where p.name = :name", Person.class);
        query.setParameter("name", name);

        return (Person) query.uniqueResult();
    }
    //end::findPerson[]

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
