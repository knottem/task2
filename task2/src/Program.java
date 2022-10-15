
import java.util.ArrayList;
import java.util.Scanner;


public class Program extends Tools{

    ArrayList<Customer> customers = new ArrayList<>();
    String filepath = "task2/src/customers.txt";
    String filepath2 = "task2/src/test.txt";
    Files files = new Files();
    boolean repeat = false;
    String[] dataFirst;
    String answer;
    String answer2;
    String answer3;

    public Customer Program() {

        files.addCustomers(customers, filepath);

        System.out.println("Välkommen till programmet");

        do {
            System.out.println("Vilken kund vill du söka efter? (Personnummer(XXXXXX-XXXX) eller namn)");
            try {
                Scanner scan = new Scanner(System.in);
                answer = scan.nextLine();

                dataFirst = answer.split(" ");

                if(answer.contains(" ")){
                    answer2 = convertText(dataFirst[0]);
                    answer3 = convertText(dataFirst[1]);
                }
                else{
                    answer2 = convertText(dataFirst[0]);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            for (int i = 0; i < customers.size(); i++) {
                String test = convertText(customers.get(i).getSurName());
                String test2 = convertText(customers.get(i).getLastName());
                if(test.equals(answer2) || test2.equals(answer2)){
                    System.out.println(customers.get(i).toString());
                    repeat = true;
                }
            }
            if(!repeat){
                System.out.println("Personen hittades inte");
            }

            repeat = repeatProgram("Kör igen?");
        }while(!repeat);
        files.createFile(customers, filepath2);
        return null;
    }
}
