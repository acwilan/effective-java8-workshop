package net.wrovira.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;
import java.util.StringJoiner;

import static net.wrovira.model.TradeAction.BUY;

public class Portfolio {

    private final HashMap<String, Holding> holdings;

    public Portfolio() {
        holdings = new HashMap<>();
    }

    public int getNumberOfHoldings() {
        return holdings.size();
    }

    public String[] getHoldingSymbols() {
        return new ArrayList<>(holdings.keySet()).toArray(new String[0]);
    }

    public Holding getHolding(final String symbol) {
        return holdings.get(symbol);
    }

    // Use the information in a trade to update the corresponding holding in the portfolio.
    // Note: a trade can a buy or a sell.
    public void modifyPortfolio(final Trade trade) {
        final Holding holding = Optional.ofNullable(holdings.get(trade.getSymbol()))
                .orElse(new Holding(trade.getSymbol()));
        if (trade.getAction() == BUY) {
            holding.addToHolding(trade.getNumOfShares(), trade.getPrice());
        } else {
            holding.removeFromHolding(trade.getNumOfShares(), trade.getPrice());
        }
        if (!holdings.containsKey(trade.getSymbol())) {
            holdings.put(trade.getSymbol(), holding);
        }
    }

    // A client should call this method to determine if the portfolio has the corresponding holding
    // and enough shares
    public boolean canSell(final String symbol, final long numOfSharesToSell) {
        return Optional.ofNullable(holdings.get(symbol))
                .map(holding -> holding.canSell(numOfSharesToSell))
                .orElse(false);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Portfolio.class.getSimpleName() + "[", "]")
                .add("holdings=" + holdings)
                .toString();
    }
}
