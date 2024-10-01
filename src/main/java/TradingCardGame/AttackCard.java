package TradingCardGame;

public class AttackCard extends Card {
    private int damage;

    public AttackCard(String name, int cost, int damage) {
        super(name, cost);
        this.damage = damage;
    }


    @Override
    public void play(Player currentPlayer, Player opponent) {
        // Calculate the final damage using the player's attack boost
        int finalDamage = currentPlayer.calculateDamage(damage);

        System.out.println(currentPlayer.getName() + " plays " + name + " and deals " + finalDamage + " damage!");
        System.out.println(" ");
        opponent.takeDamage(finalDamage);
    }

    @Override
    public void displayInfo() {
        System.out.println("This is an Attack Card. When played, it deals " + damage + " damage to the opponent.");
        System.out.println(" ");
    }
}
