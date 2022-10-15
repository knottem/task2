import org.testng.annotations.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.testng.Assert.*;

public class TestFiles {

    String testFilePath = "task2/src/customersTest.txt";
    Files files = new Files();
    Tools tools = new Tools();
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
    public void convertTextTest(){
        String test = "       GeOrGe    ";
        String test2 = tools.convertText(test);
        assertEquals(test2, "george");
        assertNotEquals(test2, "       GeOrGe    ");

    }


}
