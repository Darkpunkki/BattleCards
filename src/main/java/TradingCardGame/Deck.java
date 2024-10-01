package TradingCardGame;

import java.util.*;

public class Deck {
    private List<Card> cards;

    public Deck() {
        this.cards = new ArrayList<>();
        // Populate deck with initial cards
        // Attack Cards
        for (int i = 0; i < 2; i++) {
            cards.add(new AttackCard("Fireball", 3, 4, "Deals 4 damage"));
            cards.add(new AttackCard("Piercing Arrow", 3, 4, "Deals 4 damage"));
            cards.add(new AttackCard("Lightning Strike", 5, 8, "Deals 8 damage"));
            cards.add(new AttackCard("Meteor", 7, 10, "Deals 10 damage"));
        }

        // Defense Cards
        cards.add(new DefenseCard("Magic Barrier", 3, 4, "Provides 4 shielding"));
        cards.add(new DefenseCard("Shield Block", 4, 5, "Provides 5 shielding"));
        cards.add(new DefenseCard("Force Shield", 5, 6, "Provides 6 shielding"));
        cards.add(new DefenseCard("Iron Wall", 6, 8, "Provides 8 shielding"));

        // Buff Cards
        cards.add(new BuffCard("Killer Instincts", 2, 2, "Increases next attack by 2"));
        cards.add(new BuffCard("Power Surge", 3, 3, "Increases next attack by 3"));
        cards.add(new BuffCard("Battle Frenzy", 4, 5, "Increases next attack by 5"));
        cards.add(new BuffCard("Adrenaline Surge", 5, 7, "Increases next attack by 7"));

        // More attack cards
        cards.add(new AttackCard("Fireball", 3, 4, "Deals 4 damage"));
        cards.add(new AttackCard("Piercing Arrow", 3, 4, "Deals 4 damage"));
        cards.add(new AttackCard("Lightning Strike", 5, 8, "Deals 8 damage"));
        cards.add(new AttackCard("Meteor", 7, 10, "Deals 10 damage"));

        // Defense Cards
        cards.add(new DefenseCard("Magic Barrier", 3, 4, "Provides 4 shielding"));
        cards.add(new DefenseCard("Shield Block", 4, 5, "Provides 5 shielding"));
        cards.add(new DefenseCard("Force Shield", 5, 6, "Provides 6 shielding"));
        cards.add(new DefenseCard("Iron Wall", 6, 8, "Provides 8 shielding"));

        // Buff Cards
        cards.add(new BuffCard("Killer Instincts", 2, 2, "Increases next attack by 2"));
        cards.add(new BuffCard("Power Surge", 3, 3, "Increases next attack by 3"));
        cards.add(new BuffCard("Battle Frenzy", 4, 5, "Increases next attack by 5"));
        cards.add(new BuffCard("Adrenaline Surge", 5, 7, "Increases next attack by 7"));
        shuffle();
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public Card drawCard() {
        if (!cards.isEmpty()) {
            return cards.remove(0); // Draw the top card from the deck
        } else {
            return null; // No cards left in the deck
        }
    }

    public boolean hasCards() {
        return !cards.isEmpty();
    }
}
