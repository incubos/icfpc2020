package icfpc2020.api;

import java.math.BigInteger;
import java.util.List;

// (type, shipId, ...)
public class Command {

    public BigInteger type;
    public CommandType commandType;
    public BigInteger shipId;

    public Command(List<Object> list) {
        type = (BigInteger) list.get(0);
        try {
            commandType = CommandType.values()[type.intValue()];
        } catch (Throwable t) {}
        shipId = (BigInteger) list.get(0);
    }

    @Override
    public String toString() {
        return "Command{" +
                "type=" + type +
                ", commandType=" + commandType +
                ", shipId=" + shipId +
                '}';
    }
}

enum CommandType {
    ACCELERATE,
    DETONATE,
    SHOOT
}
