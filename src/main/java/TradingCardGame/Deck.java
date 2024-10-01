package TradingCardGame;

import java.util.*;

public class Deck {
    private List<Card> cards;

    public Deck() {
        this.cards = new ArrayList<>();
        // Populate deck with initial cards
        // Attack Cards
        for (int i = 0; i < 2; i++) {
            cards.add(new AttackCard("Fireball", 3, 4));
            cards.add(new AttackCard("Piercing Arrow", 3, 4));    // Low-cost, medium damage
            cards.add(new AttackCard("Lightning Strike", 5, 8));  // High damage, medium cost
            cards.add(new AttackCard("Meteor", 7, 10));          // Expensive, very high damage
        }

        // Defense Cards
        cards.add(new DefenseCard("Magic Barrier", 3, 4));    // Medium defense value for low cost// High-cost, high defense
        cards.add(new DefenseCard("Shield Block", 4, 5));
        cards.add(new DefenseCard("Force Shield", 5, 6));
        cards.add(new DefenseCard("Iron Wall", 6, 8));

        // Buff Cards
        cards.add(new BuffCard("Killer instincts", 2, 2));   // Provides a smaller attack boost but at a lower cost
        cards.add(new BuffCard("Power Surge", 3, 3));        // Increases attack by 3 next turn
        cards.add(new BuffCard("Battle Frenzy", 4, 5));       // Increases attack by 5 next turn
        cards.add(new BuffCard("Adrenaline Surge", 5, 7));    // Increases attack by 7 for the next turn

        // More attack cards
        cards.add(new AttackCard("Fireball", 3, 4));
        cards.add(new AttackCard("Piercing Arrow", 3, 4));    // Low-cost, medium damage
        cards.add(new AttackCard("Lightning Strike", 5, 8));  // High damage, medium cost
        cards.add(new AttackCard("Meteor", 7, 10));           // Expensive, very high damage

        // Defense Cards
        cards.add(new DefenseCard("Magic Barrier", 3, 4));    // Medium defense value for low cost// High-cost, high defense
        cards.add(new DefenseCard("Shield Block", 4, 5));
        cards.add(new DefenseCard("Force Shield", 5, 6));
        cards.add(new DefenseCard("Iron Wall", 6, 8));

        // Buff Cards
        cards.add(new BuffCard("Killer instincts", 2, 2));   // Provides a smaller attack boost but at a lower cost
        cards.add(new BuffCard("Power Surge", 3, 3));        // Increases attack by 3 next turn
        cards.add(new BuffCard("Battle Frenzy", 4, 5));       // Increases attack by 5 next turn
        cards.add(new BuffCard("Adrenaline Surge", 5, 7));    // Increases attack by 7 for the next turn


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
