package TradingCardGame;

public class IncreasingEnergyStrategy implements EnergyReplenishmentStrategy {

    private int initialEnergy;
    private int increment;

    public IncreasingEnergyStrategy(int initialEnergy, int increment) {
        this.initialEnergy = initialEnergy;
        this.increment = increment;
    }

    @Override
    public int getEnergyForTurn(int turnNumber) {
        return initialEnergy + (increment * (turnNumber - 1)); // Provide more energy as turns increase
    }
}
