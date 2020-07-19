package icfpc2020.eval.value;

import icfpc2020.Draw;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ImageValue implements LazyValue {
    @NotNull
    private final List<Draw.Coord> value;

    public ImageValue(@NotNull final List<Draw.Coord> value) {
        this.value = value;
    }

    public List<Draw.Coord> asImage() {
        return value;
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
