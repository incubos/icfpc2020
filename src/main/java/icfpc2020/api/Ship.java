package icfpc2020.api;

import icfpc2020.Draw;

import java.math.BigInteger;
import java.util.List;

// ship = (role, shipId, position, velocity, x4, x5, x6, x7)
public class Ship {
    public final Role role;
    public final BigInteger shipId;
    public final Draw.Coord position;
    public final Draw.Coord velocity;
    public final BigInteger x4;
    public final BigInteger x5;
    public final BigInteger x6;
    public final BigInteger x7;

    public Ship(List<Object> list) {
        role = Role.values()[((BigInteger) list.get(0)).intValue()];
        shipId = ((BigInteger) list.get(1));
        position = (Draw.Coord) list.get(2);
        velocity = (Draw.Coord) list.get(3);
        List<BigInteger> params = (List<BigInteger>) list.get(4);
        x4 = params.get(0);
        x5 = params.get(1);
        x6 = params.get(2);
        x7 = params.get(3);
    }

    @Override
    public String toString() {
        return "Ship{" +
                "role=" + role +
                ", shipId=" + shipId +
                ", position=" + position +
                ", velocity=" + velocity +
                ", x4=" + x4 +
                ", x4=" + x5 +
                ", x4=" + x6 +
                ", x4=" + x7 +
                '}';
    }
}
