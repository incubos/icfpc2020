package icfpc2020.galaxy;

import java.util.function.Supplier;

public class RepeatClicker implements Clicker {
    private final Supplier<Clicker> supplier;
    private final int repeat;

    Clicker clicker = null;
    private int i = 0;

    public RepeatClicker(final Supplier<Clicker> supplier, final int repeat) {
        this.supplier = supplier;
        this.repeat = repeat;
    }


    @Override
    public Vect nextClick() {
        if (clicker == null) {
            clicker = supplier.get();
            return clicker.nextClick();
        }

        if (i++ < repeat) {
            return clicker.nextClick();
        }
        i = 0;
        clicker = supplier.get();
        return clicker.nextClick();
    }
}
