package org.poo.cb;

public class AddAccountCommand implements Command{
    private final String email;
    private final String valuta;
    public AddAccountCommand(String emailAddAccount, String valuta) {
        this.email = emailAddAccount;
        this.valuta = valuta;
    }
    public void execute() {
        Banca.getInstance().addAccount(email, valuta);
    }
}
