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
        // Game ends when any player's health is <= 0
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
        Player opponent = GameUtils.getOpponent(currentPlayer, players);

        System.out.println("It's " + currentPlayer.getName() + "'s turn!");

        // Replenish energy at the start of the turn
        currentPlayer.replenishEnergy(turnNumber);

        // Draw cards at the start of the turn
        List<Card> initialDrawnCards = currentPlayer.drawCards(1);
        currentPlayer.displayCardDetails(initialDrawnCards);

        // Show player status and hand with visuals
        currentPlayer.displayStatusWithBars();
        currentPlayer.displayHandWithVisuals();

        // Input loop for playing cards, drawing more cards, or ending turn
        Scanner scanner = new Scanner(System.in);
        boolean turnEnded = false;

        while (!turnEnded) {
            currentPlayer.displayStatusWithBars();
            System.out.println(" ");
            System.out.println("Actions:");
            System.out.println("1. Play a card");
            System.out.println("2. Draw an additional card (3 energy)");
            System.out.println("3. End turn");
            System.out.println("4. View card info");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    boolean cardSelected = false;
                    while (!cardSelected) {
                        System.out.println("Choose a card to play (enter card number, or enter 0 to cancel): ");
                        currentPlayer.displayHandWithVisuals(); // Visualized hand
                        int cardIndex = scanner.nextInt() - 1;

                        if (cardIndex == -1) {
                            System.out.println("Returning to the actions menu...");
                            cardSelected = true;
                        } else if (cardIndex >= 0 && cardIndex < currentPlayer.getHand().size()) {
                            currentPlayer.playCard(cardIndex, opponent);
                            cardSelected = true;
                        } else {
                            System.out.println("Invalid card selection. Try again.");
                        }
                    }
                    break;

                case 2:
                    if (currentPlayer.getEnergy() >= 3) {
                        List<Card> newDrawnCards = currentPlayer.drawCards(1);
                        currentPlayer.spendEnergy(3);
                        currentPlayer.displayCardDetails(newDrawnCards);
                    } else {
                        System.out.println("Not enough energy to draw an additional card.");
                    }
                    break;

                case 3:
                    turnEnded = true;
                    currentPlayer.endTurn();
                    break;

                case 4:
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
