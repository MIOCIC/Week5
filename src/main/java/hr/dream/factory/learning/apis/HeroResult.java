package hr.dream.factory.learning.apis;



public class HeroResult {
    HeroList result;
}


class HeroList{
    HeroNames[] heroes;
    int count;
}

class HeroNames{
    int id;
    String name;
}