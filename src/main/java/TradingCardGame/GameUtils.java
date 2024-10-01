package TradingCardGame;

import java.util.List;

public class GameUtils {
    public static Player getOpponent(Player currentPlayer, List<Player> players) {
        // This assumes a 2-player game
        for (Player player : players) {
            if (!player.equals(currentPlayer)) {
                return player;
            }
        }
        return null;
    }
}
