package icfpc2020;

import icfpc2020.strategy.MovementStrategy;
import org.junit.Assert;
import org.junit.Test;

public class MovementStrategyTest {

    @Test
    public void testRun() {
        Assert.assertArrayEquals(new long[]{-1, -1}, MovementStrategy.axy(1, 1, 0, 0, false, 32, 128, 10));
        Assert.assertArrayEquals(new long[]{1, 1}, MovementStrategy.axy(-1, -1, 0, 0, false, 32, 128, 10));
        Assert.assertArrayEquals(new long[]{-20, 20}, MovementStrategy.axy(20, -20, 10, 10, false, 32, 128, 10));
        Assert.assertArrayEquals(new long[]{20, -20}, MovementStrategy.axy(-20, 20, 10, 10, false, 32, 128, 10));
    }

    @Test
    public void testTowards() {
        Assert.assertArrayEquals(new long[]{160, 0}, MovementStrategy.axy(160, 0, 0, 0, false, 32, 128, 10));
        Assert.assertArrayEquals(new long[]{0, 160}, MovementStrategy.axy(0, 160, 0, 0, false, 32, 128, 10));
        Assert.assertArrayEquals(new long[]{-160, 160}, MovementStrategy.axy(-160, 160, 10, 10, false, 32, 128, 10));
        Assert.assertArrayEquals(new long[]{-160, -160}, MovementStrategy.axy(-160, -160, 10, 10, false, 32, 128, 10));
    }


    @Test
    public void testParallel() {
        Assert.assertArrayEquals(new long[]{0, -1}, MovementStrategy.axy(100, 0, 0, 0, true, 32, 128, 10));
        Assert.assertArrayEquals(new long[]{0, 1}, MovementStrategy.axy(100, 0, 0, 0, false, 32, 128, 10));
        Assert.assertArrayEquals(new long[]{0, 1}, MovementStrategy.axy(100, 100, 0, 0, false, 32, 128, 10));
        Assert.assertArrayEquals(new long[]{0, 1}, MovementStrategy.axy(100, -100, 0, 0, false, 32, 128, 10));
        Assert.assertArrayEquals(new long[]{0, -1}, MovementStrategy.axy(100, -100, 0, 0, true, 32, 128, 10));
        Assert.assertArrayEquals(new long[]{0, 1}, MovementStrategy.axy(100, -100, 0, 0, false, 32, 128, 10));
    }


}
