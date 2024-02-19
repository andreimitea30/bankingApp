package org.poo.cb;

public class BuyStocksCommand implements Command {
    private final String emailBuyStocks;
    private final String numeCompanie;
    private final int amount;
    public BuyStocksCommand(String emailBuyStocks, String stockName, int amount) {
        this.emailBuyStocks = emailBuyStocks;
        this.numeCompanie = stockName;
        this.amount = amount;
    }

    @Override
    public void execute() {
        Banca.getInstance().buyStocks(emailBuyStocks, numeCompanie, amount);
    }
}
