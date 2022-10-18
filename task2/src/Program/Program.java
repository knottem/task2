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
        checkIfMembership(customers,false,null);

        System.out.println("\nVälkommen till Gym-Programmet");
        System.out.println("Dagens Datum: " + today + "\n");

        do {
            boolean startLoop = true;
            while (startLoop) {
                int answer = inputInt("Vad vill du göra?\n1. Sök efter kund\n2. Lägg till att kund varit och tränat\n3. Lägg till ny betalande kund\n4. Avsluta Programmet");
                switch (answer) {
                    case (1) -> {
                        searchCustomer("Vilken kund vill du söka efter? (Personnummer(XXXXXX-XXXX) eller namn)", customers, false,"");
                        startLoop = false;
                    }
                    case (2) -> {
                        int i = listAllCustomers("Vilken kund vill du lägga till träningstid?", customers, false, "");
                        createTrainingTime(customers, i, filepath2);
                        startLoop = false;
                    }
                    case(3) -> {
                        createNewCustomer("Skapa ny medlem.", customers,false,"");
                        startLoop = false;
                    }
                    case(4) -> System.exit(0);

                    default -> System.out.println("Felaktigt input");
                }
            }
            repeat = repeatProgram("\nKör igen?",false,"");
        }
        while (!repeat) ;

    }
}
