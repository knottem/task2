import Program.Customer;
import Program.Files;
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
    ArrayList<Customer> customerTest = new ArrayList<>();

    @Test
    public void addCustomersTest(){
        files.addCustomers(customerTest, testFilePath);
        assertEquals(customerTest.get(0).getSurName(), "George");
        assertEquals(customerTest.get(0).getLastName(), "McFly");
        assertNotEquals(customerTest.get(0).getLastName(), "Boll");
        assertEquals(customerTest.get(1).getLastName(), "Boll");

        assertEquals(customerTest.get(2).getDate(), LocalDate.of(2016,3,12));
        assertNotEquals(customerTest.get(2).getDate(), "2006-03-12");

    }
    @Test
    public void createTrainingTimeTest() {
        LocalDate date1 = LocalDate.of(2021, 11, 18);
        customerTest.add(new Customer(1234567890, "George", "Booth", date1, true));
        customerTest.add(new Customer(8205011234L, "Martin", "Arg", date1, true));
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
}
