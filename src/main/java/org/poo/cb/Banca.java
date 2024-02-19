package org.poo.cb;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Banca {
    private static Banca instance;
    private final CommandInvoker commandInvoker;
    private final List<Utilizator> utilizatori;
    private static final Map<String, Map<String, Double>> rateDeSchimb = new HashMap<>();
    private static Map<String, List<Double>> valoriActiuni;
    private Banca() {
        commandInvoker = new CommandInvoker();
        utilizatori = new ArrayList<>();
        valoriActiuni = new HashMap<>();
    }
    public static Banca getInstance() {
        if(instance == null) {
            instance = new Banca();
        }
        return instance;
    }
    public static void rateDeSchimb(String caleFisier) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(caleFisier))) {
            String linie;
            String[] valute = br.readLine().split(",");
            while ((linie = br.readLine()) != null) {
                String[] valori = linie.split(",");
                String valuta = valori[0];
                rateDeSchimb.put(valuta, new HashMap<>());
                for (int i = 1; i < valori.length; i++) {
                    rateDeSchimb.get(valuta).put(valute[i], Double.parseDouble(valori[i]));
                }
            }
        }
    }
    public static void valoriActiuni(String caleFisier) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(caleFisier))) {
            br.readLine();
            String linie;
            while ((linie = br.readLine()) != null) {
                String[] valori = linie.split(",");
                String numeCompanie = valori[0];
                List<Double> valoriActiuni = new ArrayList<>();
                for (int i = 1; i < valori.length; i++) {
                    valoriActiuni.add(Double.parseDouble(valori[i]));
                }
                Banca.valoriActiuni.put(numeCompanie, valoriActiuni);
            }
        }
    }
    public void executaComenzi(String caleFisier) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(caleFisier))) {
            String linie;
            while ((linie = br.readLine()) != null) {
                String[] parti = linie.split(" ");
                String comanda = parti[0] + " " + parti[1];
                switch (comanda) {
                    case "CREATE USER":
                        String email = parti[2];
                        String prenume = parti[3];
                        String nume = parti[4];
                        String adresa = String.join(" ", Arrays.copyOfRange(parti, 5, parti.length));
                        commandInvoker.executeCommand(new CreateUserCommand(email, nume, prenume, adresa));
                        break;
                    case "ADD FRIEND":
                        String userEmail = parti[2];
                        String friendEmail = parti[3];
                        commandInvoker.executeCommand(new AddFriendCommand(userEmail, friendEmail));
                        break;
                    case "LIST USER":
                        String userEmailList = parti[2];
                        commandInvoker.executeCommand(new ListUserCommand(userEmailList));
                        break;
                    case "ADD ACCOUNT":
                        String emailAddAccount = parti[2];
                        String valuta = parti[3];
                        commandInvoker.executeCommand(new AddAccountCommand(emailAddAccount, valuta));
                        break;
                    case "ADD MONEY":
                        String emailAddMoney = parti[2];
                        String valutaAddMoney = parti[3];
                        double suma = Double.parseDouble(parti[4]);
                        commandInvoker.executeCommand(new AddMoneyCommand(emailAddMoney, valutaAddMoney, suma));
                        break;
                    case "LIST PORTFOLIO":
                        String emailListPortfolio = parti[2];
                        commandInvoker.executeCommand(new ListPortfolioCommand(emailListPortfolio));
                        break;
                    case "EXCHANGE MONEY":
                        String emailExchangeMoney = parti[2];
                        String valutaFrom = parti[3];
                        String valutaTo = parti[4];
                        double sumaExchange = Double.parseDouble(parti[5]);
                        commandInvoker.executeCommand(new ExchangeMoneyCommand(emailExchangeMoney, valutaFrom, valutaTo, sumaExchange));
                        break;
                    case "TRANSFER MONEY":
                        String emailTransferMoney = parti[2];
                        String emailFriendTransferMoney = parti[3];
                        String valutaTransferMoney = parti[4];
                        double sumaTransferMoney = Double.parseDouble(parti[5]);
                        commandInvoker.executeCommand(new TransferMoneyCommand(emailTransferMoney, emailFriendTransferMoney, valutaTransferMoney, sumaTransferMoney));
                        break;
                    case "BUY STOCKS":
                        String emailBuyStocks = parti[2];
                        String stockName = parti[3];
                        int amount = Integer.parseInt(parti[4]);
                        commandInvoker.executeCommand(new BuyStocksCommand(emailBuyStocks, stockName, amount));
                        break;
                    case "RECOMMEND STOCKS":
                        commandInvoker.executeCommand(new RecommendStocksCommand());
                        break;
                    default:
                        break;
                }
            }
        }
    }

    public void createUser(String email, String nume, String prenume, String adresa) {
        if (existaUtilizator(email)) {
            System.out.println("User with " + email + " already exists");
            return;
        }
        Utilizator utilizator = Utilizator.newBuilder(email, nume, prenume, adresa).build();
        utilizatori.add(utilizator);
    }

    private boolean existaUtilizator(String email) {
        for (Utilizator utilizator : utilizatori) {
            if (utilizator.getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }

    public void addFriend(String userEmail, String friendEmail) {
        if (!existaUtilizator(userEmail)) {
            System.out.println("User with " + userEmail + " doesn't exist");
            return;
        }
        if (!existaUtilizator(friendEmail)) {
            System.out.println("User with " + friendEmail + " doesn't exist");
            return;
        }
        if (!getUtilizator(userEmail).getPrieteni().contains(getUtilizator(friendEmail))) {
            for (Utilizator utilizator : utilizatori) {
                if (utilizator.getEmail().equals(userEmail)) {
                    utilizator.addFriend(friendEmail);
                }
                if (utilizator.getEmail().equals(friendEmail)) {
                    utilizator.addFriend(userEmail);
                }
            }
        } else {
            System.out.println("User with" + friendEmail + "is already a friend");
        }
    }

    public Utilizator getUtilizator(String email) {
        for (Utilizator utilizator : utilizatori) {
            if (utilizator.getEmail().equals(email)) {
                return utilizator;
            }
        }
        return null;
    }

    public void listUser(String userEmail) {
        if (!existaUtilizator(userEmail)) {
            System.out.println("User with " + userEmail + " doesn't exist");
            return;
        }
        System.out.println(getUtilizator(userEmail).toString());
    }

    public void addAccount(String email, String valuta) {
        if (getUtilizator(email).getPortofoliu().existaCont(valuta)) {
            System.out.println("Account in currency " + valuta + " already exists for user");
            return;
        }
        getUtilizator(email).getPortofoliu().addAccount(valuta);
    }

    public void addMoney(String emailAddMoney, String valutaAddMoney, double suma) {
        getUtilizator(emailAddMoney).getPortofoliu().getCont(valutaAddMoney).adaugaSuma(suma);
    }

    public void listPortfolio(String emailListPortfolio) {
        if (!existaUtilizator(emailListPortfolio)) {
            System.out.println("User with " + emailListPortfolio + " doesn't exist");
            return;
        }
        System.out.println(getUtilizator(emailListPortfolio).getPortofoliu().toString());
    }

    public void exchangeMoney(String emailExchangeMoney, String valutaFrom, String valutaTo, double sumaExchange) {
        Utilizator utilizator = getUtilizator(emailExchangeMoney);
        double sumaComision = 0;
        double sumaSchimbata = sumaExchange * rateDeSchimb.get(valutaTo).get(valutaFrom);
        if (sumaSchimbata > utilizator.getPortofoliu().getCont(valutaFrom).getSuma()/2) {
            sumaComision = sumaSchimbata * 0.01;
        }
        if (utilizator.getPortofoliu().getCont(valutaFrom).getSuma() < sumaExchange + sumaComision) {
            System.out.println("Insufficient amount in account " + valutaFrom + " for exchange");
            return;
        }
        utilizator.getPortofoliu().getCont(valutaFrom).scadeSuma(sumaSchimbata + sumaComision);
        utilizator.getPortofoliu().getCont(valutaTo).adaugaSuma(sumaExchange);
    }
    public static void cleanup() {
        instance = null;
    }

    public void transferMoney(String emailTransferMoney, String emailFriendTransferMoney, String valutaTransferMoney, double sumaTransferMoney) {
        Utilizator utilizator = getUtilizator(emailTransferMoney);
        Utilizator prieten = getUtilizator(emailFriendTransferMoney);
        if (!utilizator.getPrieteni().contains(prieten)){
            System.out.println("You are not allowed to transfer money to " + emailFriendTransferMoney);
            return;
        }
        if (utilizator.getPortofoliu().getCont(valutaTransferMoney).getSuma() < sumaTransferMoney) {
            System.out.println("Insufficient amount in account " + valutaTransferMoney + " for transfer");
            return;
        }
        Transfer transfer = new Transfer();
        transfer.adaugaObservator(utilizator);
        transfer.adaugaObservator(prieten);
        transfer.transferaBani(utilizator.getEmail(), prieten.getEmail(), valutaTransferMoney, sumaTransferMoney);
        transfer.stergeObservator(prieten);
        transfer.stergeObservator(utilizator);
    }

    public void buyStocks(String emailBuyStocks, String stockName, int amount) {
        Utilizator utilizator = getUtilizator(emailBuyStocks);
        List<Double> valori = valoriActiuni.get(stockName);
        double pretActiuni = valori.get(valori.size() - 1) * amount;
        if (pretActiuni > utilizator.getPortofoliu().getCont("USD").getSuma()) {
            System.out.println("Insufficient amount in account for buying stock");
            return;
        }
        utilizator.getPortofoliu().getCont("USD").scadeSuma(pretActiuni);
        utilizator.getPortofoliu().getActiuni().add(new Actiuni(stockName));
        utilizator.getPortofoliu().getActiuni().get(utilizator.getPortofoliu().getActiuni().size() - 1).adaugaCantitate(amount);
    }

    public void recomandaActiuni() {
        List<String> recomandari = new ArrayList<>();
        double shortSMA;
        double longSMA;
        for (String numeCompanie : valoriActiuni.keySet()) {
            shortSMA = shortSMA(valoriActiuni.get(numeCompanie));
            longSMA = longSMA(valoriActiuni.get(numeCompanie));
            if (shortSMA > longSMA) {
                recomandari.add(numeCompanie);
            }
        }
        System.out.print("{\"stocksToBuy\":[");
        for (int i = 0; i < recomandari.size(); i++) {
            System.out.print("\"" + recomandari.get(i) + "\"");
            if (i < recomandari.size() - 1) {
                System.out.print(",");
            }
        }
        System.out.println("]}");
    }
    private double shortSMA(List<Double> valori) {
        double suma = 0;
        for (int i = 5; i < 10; i++) {
            suma += valori.get(i);
        }
        return suma / 5;
    }
    private double longSMA(List<Double> valori) {
        double suma = 0;
        for (Double valoare : valori) {
            suma += valoare;
        }
        return suma / valori.size();
    }
}
