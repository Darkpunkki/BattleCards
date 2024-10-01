package TradingCardGame;

public class DefenseCard extends Card {
    private int defenseValue;

    public DefenseCard(String name, int cost, int defenseValue, String desc) {
        super(name, cost, desc);
        this.defenseValue = defenseValue;
    }

    @Override
    public void play(Player currentPlayer, Player opponent) {
        System.out.println(currentPlayer.getName() + " plays " + name + " and gains " + defenseValue + " shield points!");
        currentPlayer.addDefense(defenseValue); // Add shield points to current player
        System.out.println(" ");
    }

    @Override
    public void displayInfo() {
        System.out.println("This is a Defense Card. When played, it grants " + defenseValue + " shield points, which will absorb damage.");
        System.out.println(" ");
    }
}
