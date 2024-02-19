package org.poo.cb;

import java.util.ArrayList;
import java.util.List;

public class Builder {
    protected String email;
    protected String nume;
    protected String prenume;
    protected String adresa;
    protected Portofoliu portofoliu;
    protected List<Utilizator> prieteni;
    public Builder(String email) {
        this.email = email;
    }
    public Builder setNume(String nume) {
        this.nume = nume;
        return this;
    }
    public Builder setPrenume(String prenume) {
        this.prenume = prenume;
        return this;
    }
    public Builder setAdresa(String adresa) {
        this.adresa = adresa;
        return this;
    }
    public Builder setPortofoliu(Portofoliu portofoliu) {
        this.portofoliu = portofoliu;
        return this;
    }
    public Builder setPrieteni(List<Utilizator> prieteni) {
        this.prieteni = prieteni;
        return this;
    }

    public Utilizator build() {
        return new Utilizator(this);
    }
}
