package lox;
import java.util.List;
abstract class Expr {
	static class Binary extends Expr {
		Binary (Expr left, Token op, Expr right){
			this.left = left;
			this.op = op;
			this.right = right;
		}
		final Expr left;
		final  Token op;
		final  Expr right;
	}
	static class Grouping extends Expr {
		Grouping (Expr exp){
			this.exp = exp;
		}
		final Expr exp;
	}
	static class Literal extends Expr {
		Literal (Object value){
			this.value = value;
		}
		final Object value;
	}
	static class Unary extends Expr {
		Unary (Token op, Expr right){
			this.op = op;
			this.right = right;
		}
		final Token op;
		final  Expr right;
	}
}