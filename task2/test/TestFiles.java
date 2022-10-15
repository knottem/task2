import org.junit.After;
import org.junit.Before;
import org.testng.annotations.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.util.ArrayList;

import static org.testng.Assert.*;

public class TestFiles {

    String testFilePath = "task2/src/customersTest.txt";
    Files files = new Files();
    Tools tools = new Tools();
    ArrayList<Customer> customerTest = new ArrayList<>();

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }


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
    @Test
    public void repeatProgramTest(){
        boolean repeat;
        repeat = tools.repeatProgram("Rerun", true,"j");
        assertFalse(repeat);

    }

    @Test
    public void showCustomerTest(){
        files.addCustomers(customerTest, testFilePath);
        setUpStreams();
        tools.showCustomer("question",customerTest,true,"George");
        String expectedOutput = """
                question
                Personnummer: 8701012345
                Förnamn: George
                Efternamn: McFly
                Datum: 2000-07-01
                Medlemskap: Nej""";
        assertEquals(outContent.toString().trim().replace("\r",""),expectedOutput);

        tools.showCustomer("question",customerTest,true,"BELLA");
        assertTrue(outContent.toString().contains("Förnamn: Bella"));
        assertTrue(outContent.toString().contains("Datum: 2019-12-02"));

        restoreStreams();
    }


}
