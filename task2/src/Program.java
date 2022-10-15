
import java.util.ArrayList;

public class Program extends Tools{

    ArrayList<Customer> customers = new ArrayList<>();

    String filepath = "task2/src/customers.txt";
    String filepath2 = "task2/src/testing.txt";
    Files files = new Files();

    boolean repeat = false;

    public void huvudProgram(){

        files.addCustomers(customers, filepath);

        System.out.println("Välkommen till programmet");

        do {
            int answer = inputInt("Vad vill du göra?\n1. Sök efter kund\n2. Lägg till att kund varit och tränat");
            switch (answer) {
                case (1) -> showCustomer("Vilken kund vill du söka efter? (Personnummer(XXXXXX-XXXX) eller namn)", customers);
                case (2) -> files.createFile(customers, filepath2);
                default -> System.out.println("Felaktigt input");
            }

            repeat = repeatProgram("Kör igen?");

        }while(!repeat);


    }
}
