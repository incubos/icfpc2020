package icfpc2020.eval.ast;

import icfpc2020.Draw;

import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;


public class Generator {
    public static ASTNode createListOfCoords(final List<Draw.Coord> list) {
        if (list.size() == 0) {
            return NilNode.INSTANCE;
        }
        final List<ASTNode> pairs = list.stream()
                .map(v -> new ApplyNode(new ApplyNode(ConsNode.INSTANCE,
                        new ConstantNode(BigInteger.valueOf(v.x))),
                        new ConstantNode(BigInteger.valueOf(v.y))))
                .collect(Collectors.toList());
        if (list.size() == 1) {
            return new ApplyNode(new ApplyNode(ConsNode.INSTANCE, pairs.get(0)), NilNode.INSTANCE);
        }

        // Construction from the tail = ( ) = nil
        ASTNode result = NilNode.INSTANCE;
        for (int i = pairs.size() - 1; i >= 0; i--) {
            final ASTNode head = pairs.get(i);
            result = new ApplyNode(new ApplyNode(ConsNode.INSTANCE, head), result);
        }
        return result;
    }
}
