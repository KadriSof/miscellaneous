package chapter03.application;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class UpdateRankingTest {

    RankingService service = new HibernateRankingService();
    static final String GREENARROW = "Oliver Queen";
    static final String SUPERMAN = "Clark Kent";
    static final String SQL = "SQL";

    @Test
    public void updatingExistingRanking() {
        service.addRanking(SUPERMAN, GREENARROW, SQL, 6);
        assertEquals(service.getRankingFor(SUPERMAN, SQL), 6);
        service.updateRanking(SUPERMAN, GREENARROW, SQL, 7);
        assertEquals(service.getRankingFor(SUPERMAN, SQL), 7);
    }

    @Test
    public void updateNonExistingRanking() {
        assertEquals(service.getRankingFor(SUPERMAN, SQL), 0);
        service.updateRanking(SUPERMAN, GREENARROW, SQL, 7);
        assertEquals(service.getRankingFor(SUPERMAN, SQL), 7);
    }
}
