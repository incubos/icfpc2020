package icfpc2020;

import icfpc2020.galaxy.EvalResult;
import icfpc2020.galaxy.GalaxyParser;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class GalaxyParserTest {

    @Test
    public void test() throws IOException {
        final BufferedReader reader =
                new BufferedReader(new InputStreamReader(GalaxyParser.class.getResourceAsStream("/galaxy.txt")));
        while (true) {
            final String line = reader.readLine();
            if (line == null) {
                break;
            }
            final GalaxyParser galaxyParser = new GalaxyParser();
            final EvalResult lineEval = galaxyParser.parseTextLine(line);
            Assert.assertEquals(line.trim(), lineEval.toString().trim());
        }
    }
}
