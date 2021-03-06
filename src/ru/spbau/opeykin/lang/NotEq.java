package ru.spbau.opeykin.lang;

public class NotEq extends BinaryOperation {

	public NotEq(Statement firsOperand, Statement secondOperand) {
		super(firsOperand, secondOperand);
	}

	@Override
	public Statement substitute(NameHolder name, Statement statement) {
		return new NotEq(
				firsOperand.substitute(name, statement),
				secondOperand.substitute(name, statement));
	}

	@Override
	protected String getOperationSymbol() {
		return "!=";
	}

	@Override
	protected int evaluateOperation(int first, int second) {
		return (first != second) ? 1 : 0;
	}

}
