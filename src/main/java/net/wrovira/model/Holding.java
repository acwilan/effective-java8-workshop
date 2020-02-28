package net.wrovira.model;

import java.math.BigDecimal;
import java.util.StringJoiner;

import static java.math.BigDecimal.ZERO;

public class Holding {

    private String symbol;
    private long numOfShares;
    private BigDecimal cost;

    public Holding(final String symbol) {
        this.symbol = symbol;
        this.numOfShares = 0;
        this.cost = ZERO;
    }

    public String getSymbol() {
        return symbol;
    }

    public long getNumOfShares() {
        return numOfShares;
    }

    public BigDecimal getCost() {
        return cost;
    }

    // Increase the number of shares and recalculate cost
    public void addToHolding(final long numOfShares, final BigDecimal sharePrice) {
        this.numOfShares += numOfShares;
        cost = cost.add(sharePrice.multiply(new BigDecimal(numOfShares)));
    }

    // Reduce the number of shares and recalculate cost
    public void removeFromHolding(final long numOfShares, final BigDecimal sharePrice) {
        if (numOfShares > this.numOfShares) {
            throw new IllegalArgumentException("numOfShares cannot be bigger than current numOfShares");
        }
        this.numOfShares -= numOfShares;
        cost = cost.subtract(sharePrice.multiply(new BigDecimal(numOfShares)));
    }

    // Before a sell-trade can be carried out, call this method to determine
    // if there are enough shares.
    public boolean canSell(final long numOfSharesToSell) {
        return numOfSharesToSell <= this.numOfShares;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Holding.class.getSimpleName() + "[", "]")
                .add("symbol='" + symbol + "'")
                .add("numOfShares=" + numOfShares)
                .add("cost=" + cost)
                .toString();
    }
}
