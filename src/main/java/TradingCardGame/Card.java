package TradingCardGame;

public abstract class Card {
    protected String name;
    protected int cost;

    public Card(String name, int cost) {
        this.name = name;
        this.cost = cost;
    }

    public int getCost() {
        return cost;
    }

    public String getName() {
        return name;
    }

    public abstract void play(Player currentPlayer, Player opponent);

    @Override
    public String toString() {
        return name + " (Cost: " + cost + " Energy)";
    }

    public abstract void displayInfo();
}
