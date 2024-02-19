package org.poo.cb;

public class AddFriendCommand implements Command{
    private final String userEmail;
    private final String friendEmail;
    public AddFriendCommand(String userEmail, String friendEmail) {
        this.userEmail = userEmail;
        this.friendEmail = friendEmail;
    }

    @Override
    public void execute() {
        Banca.getInstance().addFriend(userEmail, friendEmail);
    }
}
