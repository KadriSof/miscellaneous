package chapter03.simple;

import java.util.Objects;

public class Ranking {
    private Person subject;
    private Person observer;
    private Skill skill;
    private Integer ranking;
    public Ranking() {}

    public Person getSubject() {
        return subject;
    }

    public void setSubject(Person subject) {
        this.subject = subject;
    }

    public Person getObserver() {
        return observer;
    }

    public void setObserver(Person observer) {
        this.observer = observer;
    }

    public Skill getSkill() {
        return skill;
    }

    public void setSkill(Skill skill) {
        this.skill = skill;
    }

    public Integer getRanking() {
        return ranking;
    }

    public void setRanking(Integer ranking) {
        this.ranking = ranking;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getRanking());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Ranking)) return false;
        Ranking ranking = (Ranking) obj;
        return Objects.equals(getRanking(), ranking.getRanking())
                && Objects.equals(getRanking(), ranking.getSubject());
    }

    @Override
    public String toString() {
        return "Ranking{" +
                "subject=" + subject.getName() +
                ", observer=" + observer.getName() +
                ", skill=" + skill.getName() +
                ", ranking=" + ranking +
                '}';
    }
}
