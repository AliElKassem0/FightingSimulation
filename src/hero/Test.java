package hero;

public class Test {
    public static void main(String[] args) {

        Hero hero1 = new Hero("Dagobert", 100, 10);
        Hero hero2 = new Hero("Gustav", 90, 12);

        hero1.insertItem(new Item("Iron Sword", 7));
        hero1.insertItem(new Item("Shield", 5));

        hero2.insertItem(new Item("Magic Book", 16));
        hero2.insertItem(new Item("Dagger", 4));

        hero1.printEquipment();
        hero2.printEquipment();
        System.out.println();

        Hero winner = Arena.fight(hero1, hero2);

        System.out.println();
        System.out.println("The winner is: " + winner.getName() + "!");
    }
}
