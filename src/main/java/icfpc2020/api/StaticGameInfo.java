package icfpc2020.api;

import java.math.BigInteger;
import java.util.List;

// staticGameInfo staticGameInfo = (x0, role, x2, x3, x4)
public class StaticGameInfo {
    public Role role;

    public StaticGameInfo(List<Object> list) {
        role = Role.values()[((BigInteger)list.get(1)).intValue()];
    }

    @Override
    public String toString() {
        return "StaticGameInfo{" +
                "role=" + role +
                '}';
    }
}
