package TradingCardGame;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private String name;
    private int health;
    private int shield; // Additional defense points
    private int energy;
    private Deck deck;
    private List<Card> hand;
    private int attackBoost = 0; // Temporary attack boost for next turn
    private EnergyReplenishmentStrategy energyReplenishmentStrategy;

    public Player(String name, EnergyReplenishmentStrategy energyReplenishmentStrategy) {
        this.name = name;
        this.health = 40; // Starting health
        this.shield = 0;  // Shield starts at 0
        this.energy = 15; // Starting energy
        this.deck = new Deck();
        this.hand = new ArrayList<>();
        this.energyReplenishmentStrategy = energyReplenishmentStrategy;
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public int getShield() {
        return shield;
    }

    // Add defense points to shield
    public void addDefense(int defenseValue) {
        this.shield += defenseValue;
        System.out.println(name + " gains " + defenseValue + " shield points! Current shield: " + shield);
    }

    // Take damage, prioritizing shield reduction first
    public void takeDamage(int amount) {
        if (shield > 0) {
            if (amount >= shield) {
                // If damage is greater than or equal to shield, deplete shield first
                amount -= shield;
                System.out.println(name + "'s shield is broken!");
                shield = 0;
            } else {
                // Damage is less than shield, just reduce shield
                shield -= amount;
                System.out.println(name + " takes " + amount + " damage to the shield. Remaining shield: " + shield);
                return; // No health damage
            }
        }

        // Apply remaining damage to health
        health -= amount;
        if (health < 0) {
            health = 0;
        }
        System.out.println(name + " takes " + amount + " damage! Remaining health: " + health);
    }

    public void replenishEnergy(int turnNumber) {
        // Use the strategy to determine how much energy to replenish
        int energyGained = energyReplenishmentStrategy.getEnergyForTurn(turnNumber);
        energy += energyGained;
        System.out.println(name + " now has " + energy + " energy after replenishing " + energyGained + " energy on turn " + turnNumber);
    }

    public List<Card> drawCards(int numberOfCards) {
        List<Card> drawnCards = new ArrayList<>();
        for (int i = 0; i < numberOfCards; i++) {
            if (deck.hasCards()) {
                Card drawnCard = deck.drawCard();
                hand.add(drawnCard);
                drawnCards.add(drawnCard);
            } else {
                System.out.println("No more cards to draw!");
                break; // Stop drawing if no more cards are available
            }
        }
        return drawnCards; // Return the list of drawn cards
    }

    public void displayCardDetails(List<Card> drawnCards) {
        for (Card card : drawnCards) {
            System.out.println("You have drawn: " + card.getName() + " (Cost: " + card.getCost() + " Energy)");
            card.displayInfo(); // Show detailed info about the card
        }
    }


    public void displayStatus() {
        System.out.println(name + " (Health: " + health + ", Energy: " + energy + ")");
    }

    public void displayStatusWithBars() {
        System.out.println(name + "'s Status:");

        // Health Bar (Green)
        int maxHealth = 40;
        int healthBlocks = Math.max(0, Math.min(10, (int)((double)health / maxHealth * 10))); // Clamp value between 0 and 10
        String healthBar = "\033[32mHealth: [" + "█".repeat(healthBlocks) + " ".repeat(10 - healthBlocks) + "] " + health + "/" + maxHealth + "\033[0m"; // Green color for health
        System.out.println(healthBar);

        // Energy Bar (Yellow)
        int maxEnergy = 100;
        int energyBlocks = Math.max(0, Math.min(10, (int)((double)energy / maxEnergy * 10))); // Clamp value between 0 and 10
        String energyBar = "\033[33mEnergy: [" + "█".repeat(energyBlocks) + " ".repeat(10 - energyBlocks) + "] " + energy + "/" + maxEnergy + "\033[0m"; // Blue color for energy
        System.out.println(energyBar);

        // Shield Bar (Blue)
        int startShield = 20;
        int shieldBlocks = Math.max(0, Math.min(10, (int)((double)shield / startShield * 10))); // Clamp value between 0 and 20
        String shieldBar = "\033[34mShield: [" + "█".repeat(shieldBlocks) + " ".repeat(10 - shieldBlocks) + "] " + shield + "/" + startShield + "\033[0m"; // Blue color for shield
        System.out.println(shieldBar);
    }




    public void displayHand() {
        System.out.println(name + "'s Hand:");
        for (int i = 0; i < hand.size(); i++) {
            System.out.println((i + 1) + ". " + hand.get(i));
        }
    }

    public void displayHandWithVisuals() {
        System.out.println(name + "'s Hand:");

        for (int i = 0; i < hand.size(); i++) {
            Card card = hand.get(i);

            // Calculate the frame width and padding
            int frameWidth = 36;
            int innerWidth = frameWidth - 2; // Width excluding the border characters

            // Print the card frame
            System.out.println(" ");
            System.out.println("Card " + (i + 1) + ":");
            System.out.println("+------------------------------------+");
            System.out.printf("| %-34s |\n", card.getName()); // Adjust to fit 34 characters for name
            System.out.println("|------------------------------------|");
            System.out.printf("| Cost: %-28s |\n", card.getCost() + " Energy"); // Adjust for cost
            System.out.println("+------------------------------------+");

            // Print the description with wrapping if it's too long
            printDescription(card.getDesc(), innerWidth);

            System.out.println("+------------------------------------+");
        }
    }

    private void printDescription(String desc, int innerWidth) {
        String[] words = desc.split(" ");
        StringBuilder line = new StringBuilder();

        for (String word : words) {
            if (line.length() + word.length() + 1 > innerWidth) {
                // Print the current line if adding the next word exceeds the width
                System.out.printf("| %-34s |\n", line.toString().trim());
                line = new StringBuilder(); // Start a new line
            }
            line.append(word).append(" ");
        }
        // Print the remaining text in the line
        if (line.length() > 0) {
            System.out.printf("| %-34s |\n", line.toString().trim());
        }
    }



    public List <Card> getHand() {
        return hand;
    }

    public void playCard(int cardIndex, Player opponent) {
        if (cardIndex < hand.size()) {
            Card card = hand.get(cardIndex);
            if (card.getCost() <= energy) {
                // Deduct the energy
                energy -= card.getCost();

                // Play the card (execute its effect, passing current player and opponent)
                card.play(this, opponent); // Now passing both players to the card

                // Remove the card from the hand after it's played
                hand.remove(cardIndex);

                System.out.println(name + " played " + card.getName() + " for " + card.getCost() + " energy.");
                System.out.println(name + " has " + energy + " energy remaining.");
            } else {
                System.out.println("Not enough energy to play that card!");
            }
        } else {
            System.out.println("Invalid card selection!");
        }
    }



    public void endTurn() {
        System.out.println(name + " ends their turn.");
        // You can implement logic to save energy for the next turn if needed
    }

    public void spendEnergy(int i) {
        energy -= i;
    }


    public void applyAttackBoost(int attackBoost) {
        // logic to apply attack boost
        this.attackBoost = attackBoost;
    }

    // Method to calculate damage, including the attack boost
    public int calculateDamage(int baseDamage) {
        int totalDamage = baseDamage + attackBoost;
        System.out.println(name + "'s attack deals " + totalDamage + " damage (including " + attackBoost + " bonus damage).");

        // Reset the attack boost after it has been applied
        attackBoost = 0;
        return totalDamage;
    }

    public int getEnergy() {
        return energy;
    }

    public void displayCardInfo() {
        System.out.println("Card Details:");

        for (int i = 0; i < hand.size(); i++) {
            Card card = hand.get(i);
            System.out.println((i + 1) + ". " + card.getName() + " (Cost: " + card.getCost() + " Energy)");
            card.displayInfo(); // Call each card's displayInfo method to show detailed description
        }
    }

}
