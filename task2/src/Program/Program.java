package Program;

import java.time.LocalDate;
import java.util.ArrayList;

public class Program extends Tools {

    ArrayList<Customer> customers = new ArrayList<>();
    String filepath = "task2/src/customers.txt";
    String filepath2 = "task2/src/trainingTime.txt";
    LocalDate today = LocalDate.now();
    boolean repeat = false;

    public void huvudProgram() {

        addCustomers(customers, filepath);
        checkIfMembership(customers,today);

        System.out.println("\nVälkommen till Gym-Programmet");
        System.out.println("Dagens Datum: " + today + "\n");

        do {
            boolean startLoop = true;
            while (startLoop) {
                int answer = inputInt("""
                        Vad vill du göra?
                        1. Sök efter kund
                        2. Lägg till att betalande kund varit och tränat
                        3. Ändra kund till betalande kund.
                        4. Lägg till ny betalande kund
                        5. Avsluta Programmet""", false,"");
                switch (answer) {
                    case (1) -> {
                        searchCustomer("Vilken kund vill du söka efter? (Personnummer(XXXXXX-XXXX) eller namn)", customers, false,"");
                        startLoop = false;
                    }
                    case (2) -> {
                        int i = listAllCustomers("Vilken kund vill du lägga till träningstid?", customers, false, 0);
                        if(i < customers.size()) {
                            if (customers.get(i).isPayingCustomer()) {
                                createTrainingTime(customers, i, filepath2);
                            } else {
                                System.out.println("Kunden är ej betalande kund");
                            }
                        }else{
                            System.out.println("För stort nummer");
                        }
                        startLoop = false;
                    }
                    case(3) -> {
                        int i = listAllCustomers("Vilken kund vill du ändra till betalande kund", customers, false, 0);
                        if(i < customers.size()) {
                            if(!customers.get(i).isPayingCustomer()) {
                                System.out.println("Kunden: " + customers.get(i).surName + " ändrat till betalande kund");
                                customers.get(i).setPayingCustomer();
                                customers.get(i).setDate(today);
                            }else{
                                System.out.println("Kunden är redan betalande kund");
                            }
                        }else {
                            System.out.println("För stort nummer");
                        }
                        startLoop = false;
                    }
                    case(4) -> {
                        createNewCustomer("Skapa ny medlem.", customers,filepath,false,"");
                        startLoop = false;
                    }
                    case(5) -> System.exit(0);

                    default -> System.out.println("Felaktigt input");
                }
            }
            repeat = repeatProgram("\nKör igen?",false,"");
        }
        while (!repeat) ;

    }
}
