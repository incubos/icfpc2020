package icfpc2020.api;

import java.math.BigInteger;
import java.util.List;

// (type, shipId, ...)
public class Command {

    public BigInteger type;
    public BigInteger shipId;

    public Command(List<Object> list) {
        type = (BigInteger) list.get(0);
        shipId = (BigInteger) list.get(0);
    }

    @Override
    public String toString() {
        return "Command{" +
                "type=" + type +
                ", shipId=" + shipId +
                '}';
    }
}
