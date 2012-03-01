package ru.spbau.opeykin.lang;

public class Eq extends BinaryOperation {

	public Eq(Statement firsOperand, Statement secondOperand) {
		super(firsOperand, secondOperand);
	}

	@Override
	public Statement substitute(NameHolder name, Statement statement) {
		return new Eq(
				firsOperand.substitute(name, statement),
				secondOperand.substitute(name, statement));
	}

	@Override
	protected String getOperationSymbol() {
		return "==";
	}

	@Override
	protected int evaluateOperation(int first, int second) {
		return (first == second) ? 1 : 0;
	}

}
