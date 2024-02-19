package org.poo.cb;

public class ListPortfolioCommand implements Command {
    private final String emailListPortfolio;
    public ListPortfolioCommand(String emailListPortfolio) {
        this.emailListPortfolio = emailListPortfolio;
    }

    @Override
    public void execute() {
        Banca.getInstance().listPortfolio(emailListPortfolio);
    }
}
