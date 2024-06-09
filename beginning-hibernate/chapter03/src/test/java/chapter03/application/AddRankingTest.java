package chapter03.application;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class AddRankingTest {

    RankingService  service = new HibernateRankingService();

    @Test
    public void addRanking() {
        service.addRanking("Clark Kent", "Arthur Curry", "Python", 8);
        assertEquals(service.getRankingFor("Clark Kent", "Python"), 8);
    }
}
