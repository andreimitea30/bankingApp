package org.poo.cb;

public class CreateUserCommand implements Command {
    private final String email;
    private final String nume;
    private final String prenume;
    private final String adresa;
    public CreateUserCommand(String email, String nume, String prenume, String adresa) {
        this.email = email;
        this.nume = nume;
        this.prenume = prenume;
        this.adresa = adresa;
    }
    public void execute() {
        Banca.getInstance().createUser(email, nume, prenume, adresa);
    }
}
