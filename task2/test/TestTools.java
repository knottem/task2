import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestTools {
    Tools tools = new Tools();

    @Test
    public void convertTextTest(){
        String test = "       GeOrGe    ";
        String test2 = tools.convertText(test);
        assertEquals(test2, "george");
        assertNotEquals(test2, "       GeOrGe    ");

    }
}
