package org.poo.cb;

public class RecommendStocksCommand implements Command {
    public void execute() {
        Banca.getInstance().recomandaActiuni();
    }
}
