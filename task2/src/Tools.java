import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Tools {

    String[] dataFirst;
    String answer;
    String answer2;
    String answer3;

    public String convertText(String text){
        String text1 = text.toLowerCase();
        return text1.replaceAll("\\s", "");
    }

    public boolean repeatProgram(String text,boolean test, String testString) {
        boolean repeat;
        do {
            System.out.println(text +" j/n");
            Scanner input;
            if(!test) {
                input = new Scanner(System.in);
            }
            else{
                input = new Scanner(testString);
            }

            String yesNo = input.nextLine();
            repeat = true;
            switch (yesNo) {
                case "j" -> repeat = false;
                case "n" -> {
                    System.out.println("Hej då");
                    System.exit(0);
                }
                default -> System.out.println("Svara med j för JA och n för NEJ");
            }
        } while (repeat);
        return false;
    }
    public void showCustomer(String text, ArrayList<Customer> customers, boolean test, String testString){

            System.out.println(text);
            Scanner scan;
            try {
                if(!test) {
                    scan = new Scanner(System.in);
                }
                else{
                    scan = new Scanner(testString);
                }
                answer = scan.nextLine();

                if(answer.trim().contains(" ")){
                    dataFirst = answer.split(" ");
                    answer2 = convertText(dataFirst[0]);
                    answer3 = convertText(dataFirst[1]);
                }
                else{
                    answer2 = convertText(answer.trim());
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

            boolean b = false;
        for (Customer customer : customers) {
            String convert = convertText(customer.getSurName());
            String convert2 = convertText(customer.getLastName());
            if (convert.equals(answer2) || convert2.equals(answer2)) {
                System.out.println(customer);
                b = true;
            }
        }
            if(!b){
                System.out.println("Personen hittades inte");
            }

    }

    public int listAllCustomers(String text, ArrayList<Customer> customers, boolean test, String testString){
        System.out.println(text + " Svara med siffran 1-" + customers.size());
        for (int i = 0; i < customers.size(); i++) {
            System.out.println(i+1 +". "+ customers.get(i).getSurName() + " " + customers.get(i).getLastName());
        }
        return inputInt("")-1;
    }

    public int inputInt(String text){
        while(true) {
            Scanner scan = new Scanner(System.in);
            try {
                System.out.println(text);
                return scan.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Wrong Type");
                scan.next();
            } catch (NumberFormatException e) {
                System.out.println("Expected a number");
                scan.next();
            }
        }
    }


}
