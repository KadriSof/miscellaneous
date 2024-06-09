package chapter03.application;

import chapter03.hibernate.Person;
import chapter03.hibernate.Skill;
import chapter03.hibernate.Ranking;
import com.msk.hibernate.util.SessionUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.IntSummaryStatistics;
import java.util.stream.Collectors;

public class HibernateRankingService implements RankingService {

    @Override
    public int getRankingFor(String subject, String skill) {

        try (Session session = SessionUtil.getSession()) {
            Transaction tx = session.beginTransaction();
            int average = getRankingFor(session, subject, skill);
            tx.commit();

            return average;
        }
    }

    private int getRankingFor(Session session, String subject, String skill) {

        Query<Ranking> query = session.createQuery(
                "from Ranking r "
                        + "where r.subject.name = :name "
                        + "and r.skill.name = :skill", Ranking.class);
        query.setParameter("name", subject);
        query.setParameter("skill", skill);

        IntSummaryStatistics stats = query
                .list()
                .stream()
                .collect(
                        Collectors.summarizingInt(Ranking::getRanking)
                );

        return (int) stats.getAverage();
    }

    @Override
    public void addRanking(String subjectName,
                           String observerName,
                           String skillName,
                           int rank) {

        try (Session session = SessionUtil.getSession()) {
            Transaction tx = session.beginTransaction();
            addRanking(session, subjectName, observerName, skillName, rank);
            tx.commit();
        }
    }

    private void addRanking(Session session,
                            String subjectName,
                            String observerName,
                            String skillName,
                            int rank) {
        System.out.println("Adding ranking for subject " + subjectName);
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

    @Override
    public void updateRanking(String subject, String observer, String skill, int rank) {
        try (Session session = SessionUtil.getSession()) {
            Transaction tx = session.beginTransaction();

            Ranking ranking = findRanking(session, subject, observer, skill);
            if (ranking == null) {
                addRanking(session, subject, observer, skill, rank);
            } else {
                ranking.setRanking(rank);
            }

            tx.commit();
        }
    }

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

    private Person savePerson(Session session, String name) {
        Person person = findPerson(session, name);
        if (person == null) {
            person = new Person();
            person.setName(name);
            session.save(person);
        }

        return person;
    }

    private Person findPerson(Session session, String name) {
        Query<Person> query =
                session.createQuery("from Person p where p.name = :name", Person.class);
        query.setParameter("name", name);

        return (Person) query.uniqueResult();
    }

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
}
