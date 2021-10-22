package hr.dream.factory.learning.apis;

public class MatchResult {
    DetailResult result;
}

class DetailResult {
    Players[] players;
    String radiant_win;
    Picks_bans[] picks_bans;
    String match_id;
}

class Players{
    String account_id;
    int player_slot;
    int item_0;
    int item_1;
    int item_2;
    int item_3;
    int item_4;
    int item_5;
    int scaled_hero_damage;
}

class Picks_bans{
    String is_pick;
    int hero_id;
    int team;
    int order;
}