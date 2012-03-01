package ru.spbau.opeykin.lang;

public class Mul extends BinaryOperation {

	public Mul(Statement firsOperand, Statement secondOperand) {
		super(firsOperand, secondOperand);
	}

	@Override
	public Statement substitute(NameHolder name, Statement statement) {
		return new Mul(
				firsOperand.substitute(name, statement),
				secondOperand.substitute(name, statement));
	}

	@Override
	protected String getOperationSymbol() {
		return "*";
	}

	@Override
	protected int evaluateOperation(int first, int second) {
		return first * second;
	}

}
