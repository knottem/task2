import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Files extends Tools{

    String firstLine;
    String secondLine;
    String[] dataFirst;
    String[] name;
    long ssn;

    public void addCustomers(ArrayList<Customer> customers, String filepath){
            try {
                Scanner scanner = new Scanner(new File(filepath));
                while (scanner.hasNext()) {
                    firstLine = scanner.nextLine();

                    dataFirst = firstLine.split(", ");

                    secondLine = scanner.nextLine();

                    LocalDate localDate = LocalDate.parse(secondLine);

                    ssn = Long.parseLong(dataFirst[0]);

                    name = dataFirst[1].split(" ");

                    customers.add(new Customer(ssn, name[0], name[1], localDate));
                }

            }catch (Exception e){
                e.printStackTrace();
            }

    }
    public void createFile(ArrayList<Customer> customers, String filepath ){
        FileWriter writer;
        try {
            writer = new FileWriter(filepath, false);
            for (Customer customer : customers) {
                writer.write("Personnummer: " + customer.getSsn()
                        + "\nFörnamn: " + customer.getSurName()
                        + "\nEfternamn: " + customer.getLastName()
                        + "\nDatum: " + customer.getDate() + "\n\n");
            }
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
