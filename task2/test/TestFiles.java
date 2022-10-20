import Program.Customer;
import Program.Files;
import Program.Tools;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

public class TestFiles {

    String testFilePath = "task2/test/customersTest.txt";
    String testTrainingPath = "Task2/test/customerTrainingTest.txt";
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
        customerTest.add(new Customer(1234567890L, "George", "Booth", testDate));
        //customerTest.add(new Customer(1234567890L, "Martin", "Booth", testDate));
        files.createTrainingTime(customerTest, 0, testTrainingPath);

        ArrayList<String> test = new ArrayList<>();
        try (Scanner scan = new Scanner(new File(testTrainingPath))) {
            while(scan.hasNextLine()) {
                    test.add(scan.nextLine());
                }
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Kollar alltid sista tillagda i customerTrainingTest.txt
        assertEquals("Personnummer: 1234567890", test.get(test.size()-6));
        assertEquals("Förnamn: George", test.get(test.size()-5));
        assertEquals("Efternamn: Booth", test.get(test.size()-4));
        assertEquals("Datum för träning: " + LocalDate.now(), test.get(test.size()-3));
    }
    @Test
    public void addCustomerToFileTest(){
        customerTest.add(new Customer(1234567890L, "George", "Booth", LocalDate.now()));
        files.addCustomerToFile(customerTest, testFilePath);

        files.addCustomers(customerTest2, testFilePath);
        tools.checkIfMembership(customerTest2,testDate);
        assertEquals(customerTest2.get(customerTest2.size()-1).getSurName(), "George");
        assertEquals(customerTest2.get(customerTest2.size()-1).getLastName(), "Booth");
        assertEquals(customerTest2.get(customerTest2.size()-1).getDate(), LocalDate.now());
        assertTrue(customerTest2.get(customerTest2.size()-1).isPayingCustomer());

    }

}
