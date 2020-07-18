package icfpc2020;

import icfpc2020.galaxy.GalaxyEval;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.io.StringReader;

public class GalaxyEvalTest {

    @Test
    public void testSimple() throws IOException {
        Assert.assertEquals("variable = 1",
                GalaxyEval.toText(new GalaxyEval().eval(new StringReader("variable = 1"))));
    }

    @Test
    public void testApInc() throws IOException {
        Assert.assertEquals("",
                GalaxyEval.toText(new GalaxyEval().eval(new StringReader("variable = ap inc 1"))));
    }

}
