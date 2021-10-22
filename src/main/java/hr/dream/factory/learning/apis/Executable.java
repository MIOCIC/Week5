package hr.dream.factory.learning.apis;

import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Executable {
    public static void main(String[] args) {
        DotaProcessor dotaProcessor = new DotaProcessor();
        /*Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();*/
        int n = Integer.parseInt(args[0]);
        List<HeroDB> heroDBS= dotaProcessor.getDBList();
        System.out.println("Press 1 for top 10 picked hero or 2 for top 10 winrate heroes over 5 games");
        switch (n) {
            case 1:
                heroDBS.sort(Comparator.comparing(HeroDB::getPickedCounter).reversed());
                System.out.println("///////// List first 10 most picked:");
                for (int i = 0; i < 10; i++) {
                    heroDBS.get(i).setWinrate();
                    System.out.println(heroDBS.get(i).heroName + " " + heroDBS.get(i).totalSelected + " " + heroDBS.get(i).pickedCounter + " " +heroDBS.get(i).wins +" "+ heroDBS.get(i).winrate);
                }
                break;
            case 2:
                for (HeroDB heroDB : heroDBS) {
                    heroDB.setWinrate();
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
                break;
            default:
                System.out.println("A jesi bas morao...");
        }

    }

}
