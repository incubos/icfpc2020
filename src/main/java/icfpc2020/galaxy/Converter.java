package icfpc2020.galaxy;

import icfpc2020.eval.ast.ASTNode;
import icfpc2020.eval.ast.DeclarationParser;
import icfpc2020.eval.value.LazyValue;

public class Converter {

    public static Expr astToGalaxy(final ASTNode ast) {
        return GalaxyParser.parseCommand(new GalaxyParser.ParseTokens(ast.toString().split(" "), 0));
    }


    public static Expr astToGalaxy(final LazyValue value) {
        return GalaxyParser.parseCommand(new GalaxyParser.ParseTokens(value.toString().split(" "), 0));
    }


    public static ASTNode galaxyToAst(final Expr expr) {
        return DeclarationParser.parse("galaxyToAst = " + expr.toString()).node;
    }
}
