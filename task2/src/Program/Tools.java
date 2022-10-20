package Program;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Scanner;

public class Tools extends Files {

    LocalDate today = LocalDate.now();

    public String convertText(String text){
        String text1 = text.toLowerCase();
        return text1.replaceAll("\\s", "");
    }

    public void checkIfMembership(ArrayList<Customer> customers, LocalDate date){
        for (Customer customer : customers) {
            if (customer.getDate().isAfter(date.minusYears(1))) {
                customer.setPayingCustomer();
            }
        }
    }

    public void searchCustomer(String text, ArrayList<Customer> customers, boolean test, String testString){
            String[] dataFirst;
            String answer;
            String answer2 = null;
            String answer3 = null;
            long answer4 = 0;
            long answer5 = 0;

            System.out.println(text);
            Scanner scan;
            try {
                if(!test) {
                    scan = new Scanner(System.in);
                }
                else{
                    scan = new Scanner(testString);
                }
                if (scan.hasNextLong()) {
                    answer4 = scan.nextLong();
                }
                else if(scan.hasNextLine()) {

                    answer = scan.nextLine();

                    if (answer.trim().contains(" ")) {
                        dataFirst = answer.split(" ");
                        answer2 = convertText(dataFirst[0]);
                        answer3 = convertText(dataFirst[1]);
                    } else if (answer.trim().contains("-")) {
                        try {
                            answer5 = Long.parseLong(answer.replaceAll("-", ""));
                        } catch(NumberFormatException e){
                            answer2 = answer.replaceAll("-", "");
                        }
                    } else {
                        answer2 = convertText(answer.trim());
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

            boolean b = false;
        for (Customer customer : customers) {
            String convert = convertText(customer.getSurName());
            String convert2 = convertText(customer.getLastName());
            if (convert.equals(answer2) || convert2.equals(answer2) || convert2.equals(answer3)
                    || answer4 == customer.getSsn() || answer5 == customer.getSsn()) {
                    System.out.println(customer);
                b = true;
            }
        }
            if(!b){
                System.out.println("Personen hittades inte");
            }

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
                    if(!test){
                        System.out.println("Hej då");
                        System.exit(0);
                    }
                    else{
                        repeat = false;
                    }
                }
                default -> System.out.println("Svara med j för JA och n för NEJ");
            }
        } while (repeat);
        return false;
    }
    public int listAllCustomers(String text, ArrayList<Customer> customers, boolean test, int testInt){
        if(!test) {
            System.out.println(text + " Svara med siffran 1-" + customers.size());
            for (int i = 0; i < customers.size(); i++) {
                System.out.println(i + 1 + ". " + customers.get(i).getSurName() + " " + customers.get(i).getLastName()
                        + " --- Medlemskap:" + customers.get(i).getPayingCustomer());
            }
            return inputInt("",false,"") - 1;
        }
        else{
            return testInt - 1;
        }
    }

    public void createNewCustomer(String text,ArrayList<Customer> customers, String filepath, boolean test, String testString) {
        String answer, surName,lastName,surNameCap = null,lastNameCap = null, yesNo = null, testLength;
        long answer2 = 0;
        String[] dataFirst;

        System.out.println(text);
        Scanner scan;
        try {

            if (!test) {
                scan = new Scanner(System.in);
            } else {
                scan = new Scanner(testString);
            }
            if(!test) {
                System.out.println("Namn?");
            }
            answer = scan.nextLine();


            if (answer.trim().contains(" ")) {
                dataFirst = answer.split(" ");
                surName = convertText(dataFirst[0]);
                surNameCap = surName.substring(0,1).toUpperCase() + surName.substring(1);
                lastName = convertText(dataFirst[1]);
                lastNameCap = lastName.substring(0,1).toUpperCase() + lastName.substring(1);
            }

            boolean b = true;
            while(b) {
                if(!test) {
                    System.out.println("Personnummer?");
                }
                testLength = scan.nextLine();
                if (testLength.length() == 10) {
                    answer2 = Long.parseLong(testLength);
                    b = false;
                } else {
                    System.out.println(testLength + " är inte 10 siffror långt");
                }
            }


            System.out.println("Personnummer: "+ answer2 + "\nFörnamn: " + surNameCap
                    + "\nEfternamn: " + lastNameCap + "\nDagens Datum: " + today);

            if(!test) {
                System.out.println("\nStämmer detta och vill du lägga till kunde? j/n");
                yesNo = scan.nextLine();
            }

            if(Objects.equals(yesNo, "j") || test) {
                customers.add(new Customer(answer2, surNameCap, lastNameCap, today));
                if(!test) {
                    addCustomerToFile(customers, filepath);
                }
            }
            else{
                System.out.println("Ok, kunden las ej till");
            }

        }catch (Exception e) {
            e.printStackTrace();
        }

    }

    public int inputInt(String text, boolean test, String testValue) {
        while (true) {
            Scanner scan;
            System.out.println(text);

            try {
                if (!test) {
                    scan = new Scanner(System.in);
                    return scan.nextInt();
                } else {
                    new Scanner(testValue);
                    return Integer.parseInt(testValue);
                }
            } catch (InputMismatchException e) {
                if (!test) {
                    System.out.println("Förväntade mig ett nummer");
                } else {
                    break;
                }
            }catch (NumberFormatException e) {
                if (!test) {
                    System.out.println("Inte nummer");
                } else {
                    break;
                }
            }

        }
        return 0;
    }
}
