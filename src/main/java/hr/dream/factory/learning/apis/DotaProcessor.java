package hr.dream.factory.learning.apis;

import hr.dream.factory.learning.test.MySqlDao;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DotaProcessor {
    DotaFetcher dotaFetcher;

    public DotaProcessor() {
        this.dotaFetcher = new DotaFetcher();
    }

    public void fetchAndSaveAllInDb() {
        List<Matches> matchesList = dotaFetcher.fetchAllMatches();
        Set<Matches> set = new HashSet<>(matchesList);
        matchesList.clear();
        matchesList.addAll(set);
        MySqlDao dao = new MySqlDao();
        for (Matches matches : matchesList) {
            MatchResult matchResult = dotaFetcher.fetchDetailMatch(matches.match_id);
            String s= "";
            if (matchResult.result.picks_bans != null) {
                for (Picks_bans picks_bans : matchResult.result.picks_bans) {
                    s = s + picks_bans.hero_id + "," + picks_bans.is_pick + "," + picks_bans.team + ",";
                }

                String query = "INSERT INTO ListOfPicks (match_id, radiant_win, picks_bans) VALUES ('"
                        + matchResult.result.match_id + "','" + matchResult.result.radiant_win + "','" + s + "')";
                dao.insert(query);
            }
        }


    }
    public void fetchAndSavePickBans(){
        List<Matches> matchesList = dotaFetcher.fetchAllMatches();
        Set<Matches> set = new HashSet<>(matchesList);
        matchesList.clear();
        matchesList.addAll(set);
        MySqlDao dao = new MySqlDao();
        for (Matches matches : matchesList) {
            MatchResult matchResult = dotaFetcher.fetchDetailMatch(matches.match_id);
            if (matchResult.result.picks_bans != null) {
                for (Picks_bans picks_bans : matchResult.result.picks_bans) {
                    String query = "INSERT INTO ListOfPickBans (match_id, radiant_win, hero_id, is_picked, team_side ) VALUES ('"
                            + matchResult.result.match_id + "','" + matchResult.result.radiant_win + "','" + picks_bans.hero_id + "','" + picks_bans.is_pick + "','" + picks_bans.team + "')";
                    dao.insert(query);

                }

            }
        }

    }

    public void fetchAndSaveAllHeroesToDb() {
        HeroResult heroResult = dotaFetcher.fetchAllHeroes();
        MySqlDao dao = new MySqlDao();
        for (HeroNames heroNames: heroResult.result.heroes){
            String s = heroNames.name.replace("npc_dota_hero_", "");
            dao.insert("INSERT INTO ListOfHeroes (hero_id, hero_name) VALUES ('"
            + heroNames.id + "','" + s +"')");
        }
    }

    public List<HeroDB> getDBList(){
        String query = "SELECT LOH.hero_id as heroID,\n" +
                "       LOH.hero_name as heroName,\n" +
                "       count(original.hero_id) totalSelected,\n" +
                "       sum(IF(is_picked = 'true', 1, 0))\n" +
                "                     as        pickedCounter,\n" +
                "       sum(if(is_picked = 'true' &&\n" +
                "              ((original.radiant_win = 'true' && team_side = 0) || (original.radiant_win = 'false' && team_side = 1)),\n" +
                "              1, 0)) as        wins\n" +
                "from ListOfPickBans original\n" +
                "         right join ListOfHeroes LOH on original.hero_id = LOH.hero_id\n" +
                "group by LOH.hero_id, LOH.hero_name\n" +
                "order by totalSelected desc";
        MySqlDao dao = new MySqlDao();
        List<HeroDB> heroDBS = dao.select(query);
        return heroDBS;
    }



}