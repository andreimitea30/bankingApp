package org.poo.cb;

import java.util.ArrayList;
import java.util.List;

public class Transfer {
    private final List<Observer> observatori = new ArrayList<>();
    private String emailExpeditor;
    private String emailDestinatar;
    private String valuta;
    private double suma;
    public void adaugaObservator(Observer observator) {
        observatori.add(observator);
    }
    public void stergeObservator(Observer observator) {
        observatori.remove(observator);
    }
    public void notificaObservatori() {
        for(Observer observator : observatori) {
            observator.actualizeaza(emailExpeditor, emailDestinatar, valuta, suma);
        }
    }
    public void transferaBani(String emailExpeditor, String emailDestinatar, String valuta, double suma) {
        this.emailExpeditor = emailExpeditor;
        this.emailDestinatar = emailDestinatar;
        this.valuta = valuta;
        this.suma = suma;
        notificaObservatori();
    }
}
