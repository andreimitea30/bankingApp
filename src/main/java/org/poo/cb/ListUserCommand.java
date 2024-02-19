package org.poo.cb;

public class ListUserCommand implements Command{
    private final String userEmail;
    public ListUserCommand(String userEmail) {
        this.userEmail = userEmail;
    }
    public void execute() {
        Banca.getInstance().listUser(userEmail);
    }
}
