package org.poo.cb;

public class Actiuni {
    private final String numeCompanie;
    private int cantitate;
    public Actiuni(String numeCompanie) {
        this.numeCompanie = numeCompanie;
        this.cantitate = 0;
    }
    public String getNumeCompanie() {
        return numeCompanie;
    }
    public int getCantitate() {
        return cantitate;
    }
    public void adaugaCantitate(int cantitate) {
        this.cantitate += cantitate;
    }
}
