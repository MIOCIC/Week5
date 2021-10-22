package hr.dream.factory.learning.apis;

import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DotaProcessorTest {


    @Test
    void fetchAndSaveAllInDb() {
        DotaProcessor dotaProcessor = new DotaProcessor();
        dotaProcessor.fetchAndSaveAllInDb();

    }
    @Test
    void fetchAndSaveAllHeroesToDb() {
        DotaProcessor dotaProcessor = new DotaProcessor();
        dotaProcessor.fetchAndSaveAllHeroesToDb();
    }

    @Test
    void fetchAndSavePickBans() {
        DotaProcessor dotaProcessor = new DotaProcessor();
        dotaProcessor.fetchAndSavePickBans();
    }

    @Test
    void getDBList() {
        DotaProcessor dotaProcessor = new DotaProcessor();
        List<HeroDB> heroDBS= dotaProcessor.getDBList();
        for (HeroDB heroDB : heroDBS) {
            heroDB.setWinrate();
            System.out.println(heroDB.heroName + " " + heroDB.totalSelected + " " + heroDB.pickedCounter + " " + heroDB.wins + " " + heroDB.winrate);
        }
        //heroDBS.sort(new pickedComparator());
        heroDBS.sort(Comparator.comparing(HeroDB::getPickedCounter).reversed());
        System.out.println("///////// List first 5 most picked:");
        for (int i = 0; i < 5; i++) {
            System.out.println(heroDBS.get(i).heroName + " " + heroDBS.get(i).totalSelected + " " + heroDBS.get(i).pickedCounter + " " +heroDBS.get(i).wins +" "+ heroDBS.get(i).winrate);

        }
        heroDBS.sort(Comparator.comparing(HeroDB::getWinrate).reversed());
        System.out.println("///////// List first 10 with biggest winrate:");
        int i = 0;
        for (HeroDB heroDB : heroDBS) {
            if(heroDB.pickedCounter > 5) {
                System.out.println(heroDB.heroName + " " + heroDB.totalSelected + " " + heroDB.pickedCounter + " " + heroDB.wins + " " + heroDB.winrate);
                i++;
            }
            if (i==10) break;
        }


    }
}