package Program;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

public class Files {

    String firstLine;
    String secondLine;
    String[] dataFirst;
    String[] name;
    long ssn;
    LocalDate today = LocalDate.now();
    LocalTime now = LocalTime.now().withNano(0);

    public void addCustomers(ArrayList<Customer> customers, String filepath){

            try(Scanner scanner = new Scanner(new File(filepath))){
                while (scanner.hasNext()) {

                    firstLine = scanner.nextLine();

                    dataFirst = firstLine.split(", ");

                    ssn = Long.parseLong(dataFirst[0]);

                    name = dataFirst[1].split(" ");

                    secondLine = scanner.nextLine();

                    LocalDate lastPayment = LocalDate.parse(secondLine);

                    customers.add(new Customer(ssn, name[0], name[1], lastPayment, false));

                }
            }catch (Exception e){
                e.printStackTrace();
            }

    }
    public void createTrainingTime(ArrayList<Customer> customers, int position, String filepath){

        try (FileWriter writer = new FileWriter(filepath, true)){
            writer.write("Personnummer: " + customers.get(position).getSsn()
                        + "\nFörnamn: " + customers.get(position).getSurName()
                        + "\nEfternamn: " + customers.get(position).getLastName()
                        + "\nDatum för träning: " + today
                        + "\nTid för träning: " + now
                        + "\n\n");
            System.out.println("Träningstid för " + customers.get(position).getSurName()
                        + " " + customers.get(position).getLastName()
                        + " tillagd\nDatum: " + today + " Tid: " + now);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void addCustomerToFile(ArrayList<Customer> customers, String filepath){

        int position = customers.size()-1;
        try (FileWriter writer = new FileWriter(filepath, true)){
            writer.write("\n" + customers.get(position).getSsn() +", "+ customers.get(position).getSurName() + " "
                    + customers.get(position).getLastName() + "\n"+ today);
            System.out.println("Kunden " + customers.get(position).getSurName() + " " + customers.get(position).getLastName() + " tillagd.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
