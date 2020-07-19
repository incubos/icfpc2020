package icfpc2020;

import icfpc2020.api.PublicAPIImpl;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author incubos
 */
public class PublicAPIImplTest {
    @Test
    public void send() {
        assertEquals("1101000", PublicAPIImpl.INSTANCE.send("1100"));
    }
}
