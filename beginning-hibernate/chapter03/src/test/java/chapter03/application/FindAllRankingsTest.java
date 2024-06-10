package chapter03.application;

import org.testng.annotations.Test;

import java.util.Map;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;


public class FindAllRankingsTest {

    RankingService service = new HibernateRankingService();

    @Test
    public void findAllRankingsEmptySet() {
        assertEquals(service.getRankingFor("Nobody", "Java"), 0);
        assertEquals(service.getRankingFor("Nobody", "Hibernate"), 0);
        Map<String, Integer> rankings = service.findRankingsFor("Nobody");
        // make sure the dataset size is what we expect: empty.
        assertEquals(rankings.size(), 0);
    }

    @Test
    public void findAllRankings() {
        assertEquals(service.getRankingFor("Somebody", "Java"), 0);
        assertEquals(service.getRankingFor("Somebody", "Hibernate"), 0);
        service.addRanking("Somebody", "Nobody", "Java", 9);
        service.addRanking("Somebody", "Nobody", "Java", 7);
        service.addRanking("Somebody", "Nobody", "Hibernate", 6);
        service.addRanking("Somebody", "Nobody", "Hibernate", 8);
        Map<String, Integer> rankings = service.findRankingsFor("Somebody");
        assertEquals(rankings.size(), 2);
        assertNotNull(rankings.get("Java"));
        assertEquals(rankings.get("Java"), Integer.valueOf(8));
        assertNotNull(rankings.get("Hibernate"));
        assertEquals(rankings.get("Hibernate"), Integer.valueOf(7));
    }
}
