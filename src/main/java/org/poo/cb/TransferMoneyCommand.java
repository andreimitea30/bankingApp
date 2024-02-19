package org.poo.cb;

public class TransferMoneyCommand implements Command {
    private final String emailTransferMoney;
    private final String emailFriendTransferMoney;
    private final String valutaTransferMoney;
    private final double sumaTransferMoney;
    public TransferMoneyCommand(String emailTransferMoney, String emailFriendTransferMoney, String valutaTransferMoney, double sumaTransferMoney) {
        this.emailTransferMoney = emailTransferMoney;
        this.emailFriendTransferMoney = emailFriendTransferMoney;
        this.valutaTransferMoney = valutaTransferMoney;
        this.sumaTransferMoney = sumaTransferMoney;
    }

    @Override
    public void execute() {
        Banca.getInstance().transferMoney(emailTransferMoney, emailFriendTransferMoney, valutaTransferMoney, sumaTransferMoney);
    }
}
