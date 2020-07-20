package icfpc2020.api;

import org.jetbrains.annotations.NotNull;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

// staticGameInfo staticGameInfo = (x0, role, x2, x3, x4)
public class StaticGameInfo {
    public final int ticks;
    @NotNull
    public final Role role;
    @NotNull
    public final Optional<StaticGameMaxParams> maxParams;

    public StaticGameInfo(List<Object> list) {
        ticks = ((BigInteger) list.get(0)).intValue();
        role = Role.values()[((BigInteger)list.get(1)).intValue()];
        if (list.size() == 5) {
            final List<Object> value = (List<Object>) list.get(4);
            if (value == null || value.isEmpty()) {
                maxParams = Optional.empty();
            } else {
                maxParams = Optional.of(new StaticGameMaxParams(value));
            }
        } else {
            maxParams = Optional.empty();
        }
    }

    @Override
    public String toString() {
        return "StaticGameInfo{" +
                "role=" + role +
                ", maxParams=" + maxParams +
                '}';
    }
}
