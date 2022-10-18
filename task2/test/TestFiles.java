import Program.Customer;
import Program.Files;
import Program.Tools;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.util.ArrayList;

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
    public void createTrainingTime(){


    }



}
