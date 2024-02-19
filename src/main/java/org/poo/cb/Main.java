package org.poo.cb;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        if(args == null) {
            System.out.println("Running Main");
            return;
        }
        final String workingDir = "src/main/resources/";
        final String exchangeRatePath = workingDir + args[0];
        final String stockValuesPath = workingDir + args[1];
        final String commandsPath = workingDir + args[2];
        Banca banca = Banca.getInstance();
        try {
            Banca.rateDeSchimb(exchangeRatePath);
            Banca.valoriActiuni(stockValuesPath);
            banca.executaComenzi(commandsPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //cleanup
        Banca.cleanup();
    }
}