package chapter03.application;

public interface RankingService {

    int getRankingFor(String subject, String skill);
    void addRanking(String subject, String observer, String skill, int rank);
    void updateRanking(String subject, String observer, String skill, int rank);
}