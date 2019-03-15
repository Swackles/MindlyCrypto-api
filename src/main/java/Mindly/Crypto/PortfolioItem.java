package Mindly.Crypto;

import java.util.Date;

public class PortfolioItem {
    private final String name;
    private final long id;
    private final double MarketValue;
    private final long amount;
    private final Date purchased;
    private final String description;

    public PortfolioItem(long id, Currency currency, long amount, Date purchased, String description) {
        this.name = currency.getName();
        this.id = id;
        this.MarketValue = currency.getValue() * amount;
        this.amount = amount;
        this.purchased = purchased;
        this.description = description;
    }

    public String getName() { return name; }

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
