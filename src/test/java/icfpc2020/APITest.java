package icfpc2020;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author incubos
 */
public class APITest {
    @Test
    public void send() {
        assertEquals("1101000", API.send("1100"));
    }
}
