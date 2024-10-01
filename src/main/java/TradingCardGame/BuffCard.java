package TradingCardGame;

public class BuffCard extends Card {
    private int attackBoost;

    public BuffCard(String name, int cost, int attackBoost, String desc) {
        super(name, cost, desc);
        this.attackBoost = attackBoost;
    }


    @Override
    public void play(Player currentPlayer, Player opponent) {
        System.out.println(currentPlayer.getName() + " plays " + name + " and boosts attack by " + attackBoost + " for the next turn!");
        currentPlayer.applyAttackBoost(attackBoost);
        System.out.println(" ");
    }

    @Override
    public void displayInfo() {
        System.out.println("This is a Buff Card. When played, it boosts your attack by " + attackBoost + " for the next turn.");
        System.out.println(" ");
    }
}
