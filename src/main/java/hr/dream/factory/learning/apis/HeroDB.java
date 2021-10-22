package hr.dream.factory.learning.apis;

import java.util.Comparator;

public class HeroDB {
    int heroID;
    String heroName;
    int totalSelected;
    int pickedCounter;
    int wins;
    double winrate;



    public int getHeroID() {
        return heroID;
    }

    public void setHeroID(int heroID) {
        this.heroID = heroID;
    }

    public String getHeroName() {
        return heroName;
    }

    public void setHeroName(String heroName) {
        this.heroName = heroName;
    }

    public int getTotalSelected() {
        return totalSelected;
    }

    public void setTotalSelected(int totalSelected) {
        this.totalSelected = totalSelected;
    }

    public int getPickedCounter() {
        return pickedCounter;
    }

    public void setPickedCounter(int pickedCounter) {
        this.pickedCounter = pickedCounter;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    void setWinrate(){
        if(pickedCounter>0) winrate = 100 * wins / pickedCounter;
            else winrate = 0;
    }

    public double getWinrate(){
        return winrate;
    }

}

class pickedComparator implements Comparator<HeroDB>{

    @Override
    public int compare(HeroDB o1, HeroDB o2) {
        return o1.pickedCounter - o2.pickedCounter;
    }
}

