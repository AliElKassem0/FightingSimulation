package hero;

import java.util.Random;

public class Arena {
    private static final Random rand = new Random();

    public static Hero fight(Hero hero1, Hero hero2) {
        System.out.println("⚔️ Fight begins: " + hero1.getName() + " vs " + hero2.getName() + " ⚔️\n");


        while (hero1.getLifePoints() > 0 && hero2.getLifePoints() > 0) {
            boolean hero1First = rand.nextBoolean();

            if (hero1First) {
                attackRound(hero1, hero2);
                if (hero2.getLifePoints() <= 0) break;
                attackRound(hero2, hero1);
            } else {
                attackRound(hero2, hero1);
                if (hero1.getLifePoints() <= 0) break;
                attackRound(hero1, hero2);
            }
        }

        Hero winner = (hero1.getLifePoints() > 0) ? hero1 : hero2;
        System.out.println("\n🏆 Winner: " + winner.getName() + " 🏆");
        return winner;
    }

    private static void attackRound(Hero attacker, Hero defender) {
        double beforeHP = defender.getLifePoints();
        attacker.attackEquipped(defender);
        double afterHP = defender.getLifePoints();

        System.out.println("Hero '" + defender.getName() + "': " + beforeHP + "H -> " + afterHP + "H\n");
    }
}
