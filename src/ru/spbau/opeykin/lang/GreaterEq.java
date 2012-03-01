package ru.spbau.opeykin.lang;

public class GreaterEq extends BinaryOperation {

	public GreaterEq(Statement firsOperand, Statement secondOperand) {
		super(firsOperand, secondOperand);
	}

	@Override
	public Statement substitute(NameHolder name, Statement statement) {
		return new GreaterEq(
				firsOperand.substitute(name, statement),
				secondOperand.substitute(name, statement));
	}

	@Override
	protected String getOperationSymbol() {
		return ">=";
	}

	@Override
	protected int evaluateOperation(int first, int second) {
		return (first >= second) ? 1 : 0;
	}

}
