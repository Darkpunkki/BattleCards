package TradingCardGame;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TradingCardGame extends Game {

    private List<Player> players;
    private int turnNumber = 1;

    @Override
    protected void initializeGame(int numberOfPlayers) {
        // Initialize players and their decks
        players = new ArrayList<>();
        for (int i = 0; i < numberOfPlayers; i++) {
            players.add(new Player("Player " + (i + 1), new IncreasingEnergyStrategy(10, 1)));
        }
    }

    @Override
    protected boolean endOfGame() {
        // TradingCardGame.Game ends when any player's health is <= 0
        for (Player player : players) {
            if (player.getHealth() <= 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    protected void playSingleTurn(int playerIndex) {
        Player currentPlayer = players.get(playerIndex);
        Player opponent = GameUtils.getOpponent(currentPlayer, players); // Get the opponent for this turn

        System.out.println("It's " + currentPlayer.getName() + "'s turn!");

        // Replenish energy at the start of the turn
        currentPlayer.replenishEnergy(turnNumber);

        // Draw cards at the start of the turn
        List<Card> initialDrawnCards = currentPlayer.drawCards(3);
        currentPlayer.displayCardDetails(initialDrawnCards); // Display information about the cards drawn at the start

        // Show player status and hand
        currentPlayer.displayStatus();
        currentPlayer.displayHand();

        // Input loop for playing cards, drawing more cards, or ending turn
        Scanner scanner = new Scanner(System.in);
        boolean turnEnded = false;

        while (!turnEnded) {
            System.out.println(" ");
            System.out.println("Actions:");
            System.out.println("1. Play a card");
            System.out.println("2. Draw an additional card (3 energy)");
            System.out.println("3. End turn");
            System.out.println("4. View card info");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    // Play a card
                    boolean cardSelected = false;
                    while (!cardSelected) {
                        System.out.println("Choose a card to play (enter card number, or enter 0 to cancel): ");
                        currentPlayer.displayHand();

                        int cardIndex = scanner.nextInt() - 1; // Get card index, user inputs 0 to cancel

                        if (cardIndex == -1) {
                            // User chose to cancel and return to the main actions menu
                            System.out.println("Returning to the actions menu...");
                            cardSelected = true; // Exit the inner loop
                        } else if (cardIndex >= 0 && cardIndex < currentPlayer.getHand().size()) {
                            currentPlayer.playCard(cardIndex, opponent); // Pass both players (current and opponent)
                            cardSelected = true; // Exit the inner loop
                        } else {
                            System.out.println("Invalid card selection. Try again.");
                        }
                    }
                    break;

                case 2:
                    // Spend energy to draw a card
                    if (currentPlayer.getEnergy() >= 3) {
                        List<Card> newDrawnCards = currentPlayer.drawCards(1); // Draw 1 card
                        currentPlayer.spendEnergy(3); // Deduct 3 energy for drawing the card
                        currentPlayer.displayCardDetails(newDrawnCards); // Display the info about the drawn card
                    } else {
                        System.out.println("Not enough energy to draw an additional card.");
                    }
                    break;

                case 3:
                    // End the turn
                    turnEnded = true;
                    currentPlayer.endTurn();
                    break;

                case 4:
                    // View detailed info about cards in hand
                    currentPlayer.displayCardInfo();
                    break;

                default:
                    System.out.println("Invalid choice. Try again.");
                    break;
            }

            // If the player runs out of energy, automatically end the turn
            if (currentPlayer.getEnergy() <= 0) {
                System.out.println("Out of energy! Ending turn.");
                turnEnded = true;
                currentPlayer.endTurn();
            }
        }
        turnNumber++; // Increment the turn number
    }





    @Override
    protected void displayWinner() {
        // Find the player with health > 0
        for (Player player : players) {
            if (player.getHealth() > 0) {
                System.out.println(player.getName() + " wins the game!");
            }
        }
    }
}
