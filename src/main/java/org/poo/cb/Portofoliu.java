package org.poo.cb;

import java.util.ArrayList;
import java.util.List;

public class Portofoliu {
    List<Cont> conturi;
    List<Actiuni> actiuni;
    public Portofoliu() {
        conturi = new ArrayList<>();
        actiuni = new ArrayList<>();
    }
    public void addAccount(String valuta) {
        conturi.add(new Cont(valuta));
    }
    public List<Actiuni> getActiuni() {
        return actiuni;
    }
    public boolean existaCont(String valuta) {
        for(Cont cont : conturi) {
            if(cont.getTipValuta().equals(valuta)) {
                return true;
            }
        }
        return false;
    }
    public Cont getCont(String valuta) {
        for(Cont cont : conturi) {
            if(cont.getTipValuta().equals(valuta)) {
                return cont;
            }
        }
        return null;
    }
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{\"stocks\":[");
        for (int i = 0; i < actiuni.size(); i++) {
            Actiuni actiune = actiuni.get(i);
            sb.append("{\"stockName\":\"").append(actiune.getNumeCompanie()).append("\",\"amount\":").append(actiune.getCantitate()).append("}");
            if (i < actiuni.size() - 1) {
                sb.append(",");
            }
        }
        sb.append("],\"accounts\":[");
        for (int i = 0; i < conturi.size(); i++) {
            Cont cont = conturi.get(i);
            sb.append("{\"currencyName\":\"").append(cont.getTipValuta()).append("\",\"amount\":\"").append(String.format("%.2f", cont.getSuma())).append("\"}");
            if (i < conturi.size() - 1) {
                sb.append(",");
            }
        }
        sb.append("]}");
        return sb.toString();
    }
}
