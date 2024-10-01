package TradingCardGame;

public abstract class Game {

    public final void play(int numberOfPlayers) {
        initializeGame(numberOfPlayers);
        int playerInTurn = 0;
        while (!endOfGame()) {
            playSingleTurn(playerInTurn);
            playerInTurn = (playerInTurn + 1) % numberOfPlayers; // Switch turn between players
        }
        displayWinner();
    }

    // Steps that will be implemented by subclasses
    protected abstract void initializeGame(int numberOfPlayers);
    protected abstract boolean endOfGame();
    protected abstract void playSingleTurn(int player);
    protected abstract void displayWinner();
}
