package hr.dream.factory.learning.apis;

import feign.Feign;
import feign.Param;
import feign.RequestLine;
import feign.gson.GsonDecoder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

interface DotaApi{
    @RequestLine("GET /IDOTA2Match_570/GetMatchHistory/v0001/?key={token}&league_id=13256&start_at_match_id={match_id}")
    Result matchList(@Param("token") String token, @Param("match_id") String match_id);

    @RequestLine("GET /IDOTA2Match_570/GetMatchDetails/v0001/?key={token}&match_id={match_id}")
    MatchResult matchDetails(@Param("token") String token, @Param("match_id") String match_id);

    @RequestLine("GET /IEconDOTA2_570/GetHeroes/v1?key={token}")
    HeroResult heroList(@Param("token") String token);

}

public class DotaFetcher {
    private DotaApi api;
    private String token = "E01AC0AE10BC03BC29039DD8B188680D";

    public DotaFetcher() {
        this.api = Feign.builder()
                .decoder(new GsonDecoder())
                .target(DotaApi.class, "https://api.steampowered.com");
    }

    public List<Matches> fetchAllMatches(){
        List<Matches> matchesList = new ArrayList<>();
        String match_id = "0";
        Result matchHistory = this.api.matchList(token, match_id);

        while(true){
            matchesList.addAll(Arrays.asList(matchHistory.result.matches));
            if(matchHistory.results_remaining == 0) break;
            match_id = matchHistory.result.matches[matchHistory.result.matches.length -1].match_id;
            matchHistory = this.api.matchList(token, match_id);
        }

        return matchesList;

    }
    public MatchResult fetchDetailMatch(String match_id){
        MatchResult matchDetail = this.api.matchDetails(token, match_id);


        return matchDetail;
    }

    public HeroResult fetchAllHeroes(){
        HeroResult heroResult = this.api.heroList(token);

        return heroResult;
    }

}

class Matches{
    String series_id;
    String match_id;
    String radiant_team_id;
    String dire_team_id;
    String start_time;
}

class MatchHistory{
    Matches[] matches;
}

class Result{
    MatchHistory result;
    int results_remaining;
}


