package Mindly.Crypto;

import java.util.Date;

public class PortfolioItem {
    private final long id;
    private final Currency currency;
    private final long amount;
    private final Date purchased;
    private final String description;

    public PortfolioItem(long id, Currency currency, long amount, Date purchased, String description) {
        this.id = id;
        this.currency = currency;
        this.amount = amount;
        this.purchased = purchased;
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public Currency getCurrency() {
        return currency;
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
