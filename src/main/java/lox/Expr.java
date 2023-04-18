package lox;
import java.util.List;
abstract class Expr {
	interface Visitor<R>{
		R visitBinaryExpr(Binary expr);
		R visitGroupingExpr(Grouping expr);
		R visitLiteralExpr(Literal expr);
		R visitUnaryExpr(Unary expr);
	}
	static class Binary extends Expr {
		final Expr left;
		final  Token op;
		final  Expr right;

		Binary (Expr left, Token op, Expr right){
			this.left = left;
			this.op = op;
			this.right = right;
		}

		@Override
        <R> R accept(Visitor<R> visitor){
            return visitor.visitBinaryExpr(this);
        }
	}
	static class Grouping extends Expr {
		final Expr exp;

		Grouping (Expr exp){
			this.exp = exp;
		}

		@Override
        <R> R accept(Visitor<R> visitor){
            return visitor.visitGroupingExpr(this);
        }
	}
	static class Literal extends Expr {
		final Object value;

		Literal (Object value){
			this.value = value;
		}

		@Override
        <R> R accept(Visitor<R> visitor){
            return visitor.visitLiteralExpr(this);
        }
	}
	static class Unary extends Expr {
		final Token op;
		final  Expr right;

		Unary (Token op, Expr right){
			this.op = op;
			this.right = right;
		}

		@Override
        <R> R accept(Visitor<R> visitor){
            return visitor.visitUnaryExpr(this);
        }
	}
	abstract <R> R accept(Visitor<R> visitor);
}