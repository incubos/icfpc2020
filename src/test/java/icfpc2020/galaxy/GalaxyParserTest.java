package icfpc2020.galaxy;

import icfpc2020.galaxy.Assign;
import icfpc2020.galaxy.GalaxyParser;
import org.junit.Assert;
import org.junit.Test;

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
            final Assign cmd = galaxyParser.parseTextLine(line);
            final String s = cmd.toString();
            System.err.println(s);
            Assert.assertEquals(line.trim(), s.trim());
        }
    }
}
