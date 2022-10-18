import Program.Tools;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class TestTools {
    Tools tools = new Tools();
    LocalDate testDate = LocalDate.of(2022,10,18);

    @Test
    public void convertTextTest(){
        String test = "       GeOrGe    ";
        String test2 = tools.convertText(test);
        assertEquals(test2, "george");
        assertNotEquals(test2, "       GeOrGe    ");

    }
}
