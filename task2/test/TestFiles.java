import Program.Customer;
import Program.Files;
import Program.Tools;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

public class TestFiles {

    String testFilePath = "task2/test/customersTest.txt";
    String testTrainingpath = "Task2/test/customerTrainingTest.txt";
    Files files = new Files();
    Tools tools = new Tools();
    ArrayList<Customer> customerTest = new ArrayList<>();
    ArrayList<Customer> customerTest2 = new ArrayList<>();
    LocalDate testDate = LocalDate.of(2021, 11, 18);

    @Test
    public void addCustomersTest(){
        files.addCustomers(customerTest, testFilePath);
        assertEquals(customerTest.get(0).getSurName(), "George");
        assertEquals(customerTest.get(0).getLastName(), "McFly");
        assertNotEquals(customerTest.get(0).getLastName(), "Boll");
        assertEquals(customerTest.get(1).getLastName(), "Boll");

        assertEquals(customerTest.get(2).getDate(), LocalDate.of(2016,3,12));
        assertNotEquals(customerTest.get(2).getDate(), LocalDate.of(2006,3,12));

    }
    @Test
    public void createTrainingTimeTest() {
        customerTest.add(new Customer(1234567890, "George", "Booth", testDate));
        customerTest.add(new Customer(8205011234L, "Martin", "Arg", testDate));
        files.createTrainingTime(customerTest, 0, testTrainingpath);

        String[] test = new String[5];
        try (Scanner scan = new Scanner(new File(testTrainingpath))) {
            for (int i = 0; i < 5; i++) {
                test[i] = scan.nextLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertEquals("Personnummer: 1234567890", test[0]);
        assertEquals("Förnamn: George", test[1]);
        assertEquals("Efternamn: Booth", test[2]);
        assertEquals("Datum för träning: " + LocalDate.now(), test[3]);
        assertEquals("Tid för träning: " + LocalTime.now().withNano(0), test[4]);
    }
    @Test
    public void addCustomerToFileTest(){
        customerTest.add(new Customer(1234567890, "George", "Booth", LocalDate.now()));
        files.addCustomerToFile(customerTest, testFilePath);

        files.addCustomers(customerTest2, testFilePath);
        tools.checkIfMembership(customerTest2,true,testDate);
        assertEquals(customerTest2.get(customerTest2.size()-1).getSurName(), "George");
        assertEquals(customerTest2.get(customerTest2.size()-1).getLastName(), "Booth");
        assertEquals(customerTest2.get(customerTest2.size()-1).getDate(), LocalDate.now());
        assertTrue(customerTest2.get(customerTest2.size()-1).isPayingCustomer());

    }

}
