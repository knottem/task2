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

public class TestTools {
    String testFilePath = "task2/test/customersTest.txt";
    Files files = new Files();
    Tools tools = new Tools();
    ArrayList<Customer> customerTest = new ArrayList<>();
    LocalDate testDate = LocalDate.of(2022,10,20);

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
    public void convertTextTest(){
        String test = "       GeOrGe    ";
        String test2 = tools.convertText(test);
        assertEquals(test2, "george");
        assertNotEquals(test2, "       GeOrGe    ");

    }
    @Test
    public void checkIfMembershipTest(){
        LocalDate date1 = LocalDate.of(2020,10,18);
        LocalDate date2 = LocalDate.of(2021,11,18);
        customerTest.add(new Customer(8701012345L,"George", "Booth",date1));
        customerTest.add(new Customer(1234567890L,"Mika", "Dante",date2));
        tools.checkIfMembership(customerTest,true,testDate);

        assertFalse(customerTest.get(0).isPayingCustomer());
        assertTrue(customerTest.get(1).isPayingCustomer());
    }
    @Test
    public void searchCustomerTest(){
        LocalDate date1 = LocalDate.of(2000,7,1);
        LocalDate date2 = LocalDate.of(2021,11,18);
        customerTest.add(new Customer(8701012345L,"George", "McFly",date1));
        setUpStreams();
        tools.searchCustomer("Vilken kund vill du söka efter? (Personnummer(XXXXXX-XXXX) eller namn)",customerTest,true,"George");
        String expectedOutput = """
                Vilken kund vill du söka efter? (Personnummer(XXXXXX-XXXX) eller namn)
                Personnummer: 870101-2345
                Förnamn: George
                Efternamn: McFly
                Datum: 2000-07-01
                Medlemskap: Nej""";
        assertEquals(outContent.toString().trim().replace("\r",""),expectedOutput);

        customerTest.add(new Customer(1234567890L, "Bella", "Boll", date2));
        tools.searchCustomer("question",customerTest,true,"BeLLA boLL");
        assertTrue(outContent.toString().contains("Förnamn: Bella"));
        assertTrue(outContent.toString().contains("Datum: 2021-11-18"));
        restoreStreams();

    }
    @Test
    public void repeatProgramTest(){
        //Vill att repeat blir false för man svarade JA med j, så hela programmet körs om.
        boolean repeat = tools.repeatProgram("Test Rerun", true,"j");
        assert(!repeat);
        //repeat2 borde också bli false, då jag har istället för system.exit repeat false i test.
        boolean repeat2 = tools.repeatProgram("Test Rerun", true,"n");
        assert(!repeat2);


    }

    @Test
    public void listAllCustomersTest(){
        //Skall alltid returna index platsen på arrayListen, så returnar -1 av det man valde.
        files.addCustomers(customerTest, testFilePath);
        int i = tools.listAllPayingCustomers("test", customerTest, true, 1);
        int j = tools.listAllPayingCustomers("test", customerTest, true, 3);
        assertEquals(i,0);
        assertEquals(j,2);
    }
    @Test
    public void createNewCustomerTest(){
        files.addCustomers(customerTest, testFilePath);
        String test = "Erik Test\n1234567890";
        tools.createNewCustomer("test",customerTest,testFilePath,true,test);

        assertEquals(customerTest.get(customerTest.size()-1).getSurName(), "Erik");
        assertEquals(customerTest.get(customerTest.size()-1).getLastName(), "Test");
        assertEquals(customerTest.get(customerTest.size()-1).getSsn(), 1234567890);
        assertEquals(customerTest.get(customerTest.size()-1).getDate(), LocalDate.now());
    }
    @Test
    public void inputIntTest(){
        assertEquals(tools.inputInt("",true,"1"), 1);
        assertNotEquals(tools.inputInt("",true,"one"), 1);
        assertEquals(tools.inputInt("",true,"20"), 20);

        //Borde returna noll då den blir IndexOutOfBoundsException
        assertEquals(tools.inputInt("",true,"1231251251251"), 0);
    }

}
