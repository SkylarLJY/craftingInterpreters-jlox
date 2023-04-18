package lox;

import lox.Expr.Binary;
import lox.Expr.Grouping;
import lox.Expr.Literal;
import lox.Expr.Unary;

class AstPrinter implements Expr.Visitor<String> {

    String print(Expr expr){
        return expr.accept(this);
    }

    private String parenthesize(String name, Expr... exprs){
        StringBuilder builder = new StringBuilder();
        builder.append("(").append(name);
        for (Expr expr: exprs){
            builder.append(" ").append(expr.accept(this));
        }
        builder.append(")");
        return builder.toString();
    }

    @Override
    public String visitBinaryExpr(Binary expr) {
        return parenthesize(expr.op.lexeme, expr.left, expr.right);
    }

    @Override
    public String visitGroupingExpr(Grouping expr) {
        return parenthesize("Grouping", expr.exp);
    }

    @Override
    public String visitLiteralExpr(Literal expr) {
        if (expr.value == null) return "nil";
        return expr.value.toString();
    }

    @Override
    public String visitUnaryExpr(Unary expr) {
        return parenthesize(expr.op.lexeme, expr.right);
    }

    public static void main(String[] args){
        // (* (- 111) (grouping 222))
        Expr grouping = new Grouping(new Literal(222));
        Expr neg = new Unary(new Token(TokenType.MINUS, "-", null, 1), new Literal(111));
        Expr expr = new Binary(neg, new Token(TokenType.STAR, "*", null, 1), grouping);
        System.out.println(new AstPrinter().print(expr));
            
    }
  
}
