import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class Files extends Tools{

    String firstLine;
    String secondLine;
    String[] dataFirst;
    String[] name;
    long ssn;
    LocalDate today = LocalDate.now();

    public void addCustomers(ArrayList<Customer> customers, String filepath){
            try {
                Scanner scanner = new Scanner(new File(filepath));
                while (scanner.hasNext()) {
                    firstLine = scanner.nextLine();

                    dataFirst = firstLine.split(", ");

                    secondLine = scanner.nextLine();

                    LocalDate lastPayment = LocalDate.parse(secondLine);


                    ssn = Long.parseLong(dataFirst[0]);

                    name = dataFirst[1].split(" ");

                    if(lastPayment.isAfter(today.minusYears(1))){
                        customers.add(new Customer(ssn, name[0], name[1], lastPayment, true));
                    }
                    else{
                        customers.add(new Customer(ssn, name[0], name[1], lastPayment, false));
                    }

                }

            }catch (Exception e){
                e.printStackTrace();
            }

    }
    public void createTrainingTime(ArrayList<Customer> customers, int position, String filepath ){
        FileWriter writer;
        try {
            writer = new FileWriter(filepath, true);
            writer.write("Personnummer: " + customers.get(position).getSsn()
                        + "\nFörnamn: " + customers.get(position).getSurName()
                        + "\nEfternamn: " + customers.get(position).getLastName()
                        + "\nDatum för träning: " + today + "\n\n");
            System.out.println("Träningstid för " + customers.get(position).getSurName() + " " + customers.get(position).getLastName() + " tillagd");

            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
