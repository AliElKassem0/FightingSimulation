package hero;

public class Item {
    private static int nextId = 0;

    private String name;
    private double damageBonus;
    private final int id;

    public Item(String name, double damageBonus) {
        this.name = name;
        setDamageBonus(damageBonus);
        this.id = nextId++;
    }

    public String getName() {
        return name;
    }

    public double getDamageBonus() {
        return damageBonus;
    }

    public int getId() {
        return id;
    }

    public void setDamageBonus(double dmg) {
        if (dmg < 0 || dmg > 100) {
            this.damageBonus = Math.random() * 100;
        } else {
            this.damageBonus = dmg;
        }
    }

    public void printInfo() {
        System.out.println("Item '" + name + "' (ID: " + id + "): +" + damageBonus + " D");
    }
}
