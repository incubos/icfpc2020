package icfpc2020;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ModulateTest {
    private static final Logger log = LoggerFactory.getLogger(ModulateTest.class);

    @Test
    public void test() {
        Assert.assertEquals(new Modulate(0L).mod(), "010");
        Assert.assertEquals(new Modulate(1L).mod(), "01001");
        Assert.assertEquals(new Modulate(-1L).mod(), "10001");
    }
}
