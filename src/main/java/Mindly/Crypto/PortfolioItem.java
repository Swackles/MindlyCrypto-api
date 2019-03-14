package Mindly.Crypto;

import java.util.Date;

public class PortfolioItem {
    private final long id;
    private final double MarketValue;
    private final long amount;
    private final Date purchased;
    private final String description;

    public PortfolioItem(long id, float value, long amount, Date purchased, String description) {
        this.id = id;
        this.MarketValue = value * amount;
        this.amount = amount;
        this.purchased = purchased;
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public double getMarketValue() {
        return MarketValue;
    }

    public  long getAmount() {
        return  amount;
    }

    public Date getPurchased() {
        return  purchased;
    }

    public String getDescription() {
        return  description;
    }
}
