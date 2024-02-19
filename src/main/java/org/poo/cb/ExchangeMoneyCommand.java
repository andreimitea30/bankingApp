package org.poo.cb;

public class ExchangeMoneyCommand implements Command {
    private final String emailExchangeMoney;
    private final String valutaFrom;
    private final String valutaTo;
    private final double sumaExchange;
    public ExchangeMoneyCommand(String emailExchangeMoney, String valutaFrom, String valutaTo, double sumaExchange) {
        this.emailExchangeMoney = emailExchangeMoney;
        this.valutaFrom = valutaFrom;
        this.valutaTo = valutaTo;
        this.sumaExchange = sumaExchange;
    }

    @Override
    public void execute() {
        Banca.getInstance().exchangeMoney(emailExchangeMoney, valutaFrom, valutaTo, sumaExchange);
    }
}
