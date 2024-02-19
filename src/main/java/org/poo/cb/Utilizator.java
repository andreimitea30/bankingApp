package org.poo.cb;

import java.util.ArrayList;
import java.util.List;

public class Utilizator implements Observer{
    private final String email;
    private final String nume;
    private final String prenume;
    private final String adresa;
    private Portofoliu portofoliu;
    private List<Utilizator> prieteni;
    protected Utilizator(Builder builder) {
        this.email = builder.email;
        this.nume = builder.nume;
        this.prenume = builder.prenume;
        this.adresa = builder.adresa;
        this.portofoliu = builder.portofoliu;
        this.prieteni = builder.prieteni;
    }
    public static Builder newBuilder(String email, String nume, String prenume, String adresa) {
        return new Builder(email).setNume(nume).setPrenume(prenume).setAdresa(adresa).setPortofoliu(new Portofoliu()).setPrieteni(new ArrayList<>());
    }
    public String getEmail() {
        return email;
    }
    public void addFriend(String friendEmail) {
        this.prieteni.add(Banca.getInstance().getUtilizator(friendEmail));
    }
    public List<Utilizator> getPrieteni() {
        return prieteni;
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        sb.append("\"email\":\"").append(email).append("\",");
        sb.append("\"firstname\":\"").append(prenume).append("\",");
        sb.append("\"lastname\":\"").append(nume).append("\",");
        sb.append("\"address\":\"").append(adresa).append("\",");
        sb.append("\"friends\":[");

        // Add friends' emails
        for (int i = 0; i < prieteni.size(); i++) {
            sb.append("\"").append(prieteni.get(i).getEmail()).append("\"");
            if (i < prieteni.size() - 1) {
                sb.append(",");
            }
        }

        sb.append("]}");
        return sb.toString();
    }
    public Portofoliu getPortofoliu() {
        return portofoliu;
    }
    public void actualizeaza(String emailExpeditor, String emailDestinatar, String valuta, double suma) {
        if (email.equals(emailExpeditor)) {
            portofoliu.getCont(valuta).scadeSuma(suma);
        }
        if (email.equals(emailDestinatar)) {
            portofoliu.getCont(valuta).adaugaSuma(suma);
        }
    }
}
