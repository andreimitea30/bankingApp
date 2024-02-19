package org.poo.cb;

public class Cont {
    private final String tipValuta;
    private double suma;
    public Cont(String tipValuta) {
        this.tipValuta = tipValuta;
        this.suma = 0;
    }
    public void adaugaSuma(double suma) {
        this.suma += suma;
    }
    public void scadeSuma(double suma) {
        this.suma -= suma;
    }
    public String getTipValuta() {
        return tipValuta;
    }
    public double getSuma() {
        return suma;
    }
}
