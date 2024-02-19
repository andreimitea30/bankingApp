package org.poo.cb;

public interface Observer {
    // interfata pentru implementarea DP-ului Observer
    void actualizeaza(String emailExpeditor, String emailDestinatar, String valuta, double suma);
}
