
import java.util.ArrayList;

public class Program extends Tools {

    ArrayList<Customer> customers = new ArrayList<>();

    String filepath = "task2/src/customers.txt";
    String filepath2 = "task2/src/testing.txt";
    Files files = new Files();

    boolean repeat = false;

    public void huvudProgram() {

        files.addCustomers(customers, filepath);

        System.out.println("Välkommen till programmet");

        do {
            boolean startLoop = true;
            while (startLoop) {
                int answer = inputInt("Vad vill du göra?\n1. Sök efter kund\n2. Lägg till att kund varit och tränat");
                switch (answer) {
                    case (1) -> {
                        showCustomer("Vilken kund vill du söka efter? (Personnummer(XXXXXX-XXXX) eller namn)", customers, false,"");
                        startLoop = false;
                    }
                    case (2) -> {
                        int i = files.listAllCustomers("Vilken kund vill du lägga till träningstid?", customers, false, "");
                        files.createTrainingTime(customers, i-1, filepath2);
                        startLoop = false;
                    }
                    default -> System.out.println("Felaktigt input");
                }
            }
            repeat = repeatProgram("Kör igen?",false,"");
        }
        while (!repeat) ;

    }
}
