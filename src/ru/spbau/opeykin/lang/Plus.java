package ru.spbau.opeykin.lang;

public class Plus extends BinaryOperation {

	@Override
	public Statement substitute(NameHolder name, Statement statement) {
		return new Plus(
				firsOperand.substitute(name, statement),
				secondOperand.substitute(name, statement));
	}

	@Override
	protected int evaluateOperation(int first, int second) {
		return first + second;
	}

	public Plus(Statement firsOperand, Statement secondOperand) {
		super(firsOperand, secondOperand);
	}

	@Override
	protected String getOperationSymbol() {
		return "+";
	}
}
