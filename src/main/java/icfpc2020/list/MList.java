package icfpc2020.list;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * TODO
 *
 * @author alesavin
 */
public class MList {
    private List<MList> list;
    private BigInteger value;

    public MList() {
        list = new ArrayList<>();
    }
    public MList(BigInteger bi) {
        this.value = bi;
    }

    public boolean isList() {
        return list != null;
    }

    public boolean isNil() {
        return isList() && list.isEmpty();
    }

    public List<MList> getList() {
        return list;
    }

    public MList getChild(int index) {
        if (!isList()) throw new IllegalArgumentException("Can't get index " + index + " of value node");
        return list.get(index);
    }

    public BigInteger getValue() {
        return value;
    }

    @Override
    public String toString() {
        if (isNil()) {
            return "nil";
        } else if (isList()) {
            return list
                    .stream()
                    .map(MList::toString)
                    .collect(Collectors.joining(",", "[", "]"));
        } else {
            return value.toString();
        }
    }
}