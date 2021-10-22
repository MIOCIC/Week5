package hr.dream.factory.learning.apis;

public class HeroWinrate extends HeroDB{
    double winrate;

    {
        winrate = 100*wins / pickedCounter;
    }

}
