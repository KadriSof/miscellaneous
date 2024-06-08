package chapter03.simple;

import org.testng.annotations.Test;

public class ModelTest {

    @Test
    public void testModelCreation() {
        Person subject = new Person();
        subject.setName("Bruce Wayne");

        Person observer = new Person();
        observer.setName("Clark Kent");

        Skill skill = new Skill();
        skill.setName("Java");

        Ranking ranking = new Ranking();
        ranking.setSubject(subject);
        ranking.setObserver(observer);
        ranking.setSkill(skill);
        ranking.setRanking(8);

        System.out.println(ranking);
    }
}
