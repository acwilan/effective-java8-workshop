package net.wrovira;

import net.wrovira.model.Portfolio;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Date;

import static net.wrovira.model.Trade.aTrade;
import static net.wrovira.model.TradeAction.BUY;
import static net.wrovira.model.TradeAction.SALE;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.fail;

public class PortfolioTest {

    private static final String APPLE_MARKET_SYMBOL = "AAPL";
    private static final String INTEL_MARKET_SYMBOL = "INTC";

    @Test
    public void marketDayTest() {
        final Portfolio portfolio = new Portfolio();
        portfolio.modifyPortfolio(
                aTrade()
                        .withSymbol(APPLE_MARKET_SYMBOL)
                        .withAction(BUY)
                        .withPrice(new BigDecimal(550.00))
                        .withNumOfShares(100)
                        .withTradeDate(new Date())
                        .build()
        );
        assertThat(portfolio.getNumberOfHoldings(), is(1));
        assertThat(portfolio.getHolding(APPLE_MARKET_SYMBOL), is(notNullValue()));
        assertThat(portfolio.getHolding(APPLE_MARKET_SYMBOL).getCost(), is(new BigDecimal(55000.0)));

        if (portfolio.canSell(APPLE_MARKET_SYMBOL, 50)) {
            portfolio.modifyPortfolio(
                    aTrade()
                            .withSymbol(APPLE_MARKET_SYMBOL)
                            .withAction(SALE)
                            .withPrice(new BigDecimal(580.00))
                            .withNumOfShares(50)
                            .withTradeDate(new Date())
                            .build()
            );
        } else {
            fail("Should allow to sell AAPL shares");
        }
        assertThat(portfolio.getNumberOfHoldings(), is(1));
        assertThat(portfolio.getHolding(APPLE_MARKET_SYMBOL), is(notNullValue()));
        assertThat(portfolio.getHolding(APPLE_MARKET_SYMBOL).getCost(), is(new BigDecimal(26000)));

        portfolio.modifyPortfolio(
                aTrade()
                        .withSymbol(INTEL_MARKET_SYMBOL)
                        .withAction(BUY)
                        .withPrice(new BigDecimal(21.00))
                        .withNumOfShares(100)
                        .withTradeDate(new Date())
                        .build()
        );
        assertThat(portfolio.getNumberOfHoldings(), is(2));
        assertThat(portfolio.getHolding(INTEL_MARKET_SYMBOL), is(notNullValue()));
        assertThat(portfolio.getHolding(INTEL_MARKET_SYMBOL).getCost(), is(new BigDecimal(2100)));

        final String[] symbols = portfolio.getHoldingSymbols();
        assertThat(symbols.length, is(2));
        assertThat(symbols[0], is(APPLE_MARKET_SYMBOL));
        assertThat(symbols[1], is(INTEL_MARKET_SYMBOL));

    }
}
