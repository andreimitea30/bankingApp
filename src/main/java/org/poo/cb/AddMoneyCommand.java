package org.poo.cb;

public class AddMoneyCommand implements Command {
    private final String emailAddMoney;
    private final String valutaAddMoney;
    private final double suma;
    public AddMoneyCommand(String emailAddMoney, String valutaAddMoney, double suma) {
        this.emailAddMoney = emailAddMoney;
        this.valutaAddMoney = valutaAddMoney;
        this.suma = suma;
    }

    @Override
    public void execute() {
        Banca.getInstance().addMoney(emailAddMoney, valutaAddMoney, suma);
    }
}
