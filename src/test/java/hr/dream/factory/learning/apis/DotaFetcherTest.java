package hr.dream.factory.learning.apis;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.List;

class DotaFetcherTest {

    @org.junit.jupiter.api.Test
    void fetchAllMatches() {
        DotaFetcher fetcher = new DotaFetcher();
        List<Matches> matchesList =fetcher.fetchAllMatches();
        for (Matches matches : matchesList){
            System.out.println(matches.match_id);
        }
        System.out.println(matchesList.size());
        Assert.assertEquals(210, matchesList.size());
    }

    @Test
    void fetchDetailMatch() {
        String match_id = "6081547301";
        DotaFetcher fetcher = new DotaFetcher();
        MatchResult matchResultResult = fetcher.fetchDetailMatch(match_id);
        for(Picks_bans picks_bans : matchResultResult.result.picks_bans) {
            System.out.println(""+ picks_bans.hero_id + picks_bans.team + picks_bans.is_pick);
        }

    }

    @Test
    void fetchAllHeroes() {
        DotaFetcher fetcher = new DotaFetcher();
        HeroResult heroResult = fetcher.fetchAllHeroes();
        for(HeroNames heroNames : heroResult.result.heroes){
            System.out.println(heroNames.id+heroNames.name);

        }
    }
}