package ru.spbau.opeykin.lang;

public class Less extends BinaryOperation {

	public Less(Statement firsOperand, Statement secondOperand) {
		super(firsOperand, secondOperand);
	}

	@Override
	public Statement substitute(NameHolder name, Statement statement) {
		return new Less(
				firsOperand.substitute(name, statement),
				secondOperand.substitute(name, statement));
	}

	@Override
	protected String getOperationSymbol() {
		return "<";
	}

	@Override
	protected int evaluateOperation(int first, int second) {
		return (first < second) ? 1 : 0;
	}

}
