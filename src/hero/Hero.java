package hero;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Hero {

    private static final Random random = new Random();

    private String name;
    private double lifePoints;
    private double damagePoints;

    private List<Item> equipment;
    private int maxEquipmentSize;

    public Hero(String name, double lifePoints, double damagePoints) {
        this(name, lifePoints, damagePoints, 6);
    }

    public Hero(String name, double lifePoints, double damagePoints, int bagSize) {
        setName(name);
        this.lifePoints = lifePoints;
        this.damagePoints = damagePoints;

        if (bagSize < 1) bagSize = 1;
        if (bagSize > 15) bagSize = 15;

        this.maxEquipmentSize = bagSize;
        this.equipment = new ArrayList<>();
    }

    public void setName(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Hero name must have at least one character");
        }
        this.name = name;
    }


    public String getName() {
        return name;
    }

    public double getLifePoints() {
        return lifePoints;
    }

    public void setLifePoints(double lifePoints) {
        this.lifePoints = Math.max(0, lifePoints);
    }

    public boolean insertItem(Item item) {
        if (equipment.size() < maxEquipmentSize) {
            equipment.add(item);
            return true;
        }
        return false;
    }

    public int getEquippedDamage() {
        double total = damagePoints;
        for (Item item : equipment) {
            total += item.getDamageBonus();
        }
        return (int) total;
    }

    public void attackEquipped(Hero target) {
        if (target.evade()) {
            System.out.println(target.name + " teleported to another dimension! Attack missed!");
        } else {
            int damage = getEquippedDamage();
            target.setLifePoints(target.getLifePoints() - damage);
            System.out.println(name + " attacks " + target.getName() + " for " + damage + " damage.");

            dropRandomItem();
            stealRandomItem(target);
        }

        if (doubleAttack()) {
            int damage = (int) damagePoints;
            target.setLifePoints(target.getLifePoints() - damage);
            System.out.println(name + " performs a double attack for " + damage + " damage!");
        }
    }

    private boolean evade() {
        return random.nextDouble() < 0.3;
    }

    private void dropRandomItem() {
        if (!equipment.isEmpty() && random.nextDouble() < 0.2) {
            int index = random.nextInt(equipment.size());
            Item lost = equipment.remove(index);
            System.out.println(name + " clumsily drops " + lost.getName() + "!");
        }
    }

    private void stealRandomItem(Hero target) {
        if (!target.equipment.isEmpty() && random.nextDouble() < 0.4) {
            int index = random.nextInt(target.equipment.size());
            Item stolen = target.equipment.remove(index);
            boolean added = insertItem(stolen);
            System.out.println(name + " steals " + stolen.getName() + " from " + target.getName() + (added ? "!" : " (no space!)"));
        }
    }

    private boolean doubleAttack() {
        return random.nextDouble() < 0.1;
    }

    public void printEquipment() {
        System.out.println(name + "'s equipment:");
        if (equipment.isEmpty()) {
            System.out.println("* (empty)");
        } else {
            for (Item item : equipment) {
                item.printInfo();
            }
        }
    }
}
