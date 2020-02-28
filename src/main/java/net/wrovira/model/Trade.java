package net.wrovira.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.StringJoiner;

public class Trade {

    private final String symbol;
    private final TradeAction action;
    private final BigDecimal price;
    private final int numOfShares;
    private final Date tradeDate;

    private Trade(final Builder builder) {
        symbol = builder.symbol;
        action = builder.action;
        price = builder.price;
        numOfShares = builder.numOfShares;
        tradeDate = builder.tradeDate;
    }

    public static Builder aTrade() {
        return new Builder();
    }

    public String getSymbol() {
        return symbol;
    }

    public TradeAction getAction() {
        return action;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public int getNumOfShares() {
        return numOfShares;
    }

    public Date getTradeDate() {
        return tradeDate;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Trade.class.getSimpleName() + "[", "]")
                .add("symbol='" + symbol + "'")
                .add("action=" + action)
                .add("price=" + price)
                .add("numOfShares=" + numOfShares)
                .add("tradeDate=" + tradeDate)
                .toString();
    }

    public static final class Builder {
        private String symbol;
        private TradeAction action;
        private BigDecimal price;
        private int numOfShares;
        private Date tradeDate;

        private Builder() {
        }

        public Builder withSymbol(final String symbol) {
            this.symbol = symbol;
            return this;
        }

        public Builder withAction(final TradeAction action) {
            this.action = action;
            return this;
        }

        public Builder withPrice(final BigDecimal price) {
            this.price = price;
            return this;
        }

        public Builder withNumOfShares(final int numOfShares) {
            this.numOfShares = numOfShares;
            return this;
        }

        public Builder withTradeDate(final Date tradeDate) {
            this.tradeDate = tradeDate;
            return this;
        }

        public Trade build() {
            return new Trade(this);
        }
    }
}
