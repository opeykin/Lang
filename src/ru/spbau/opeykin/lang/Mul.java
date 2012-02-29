package ru.spbau.opeykin.lang;

public class Mul extends BinaryOperation {

	public Mul(Statement firsOperand, Statement secondOperand) {
		super(firsOperand, secondOperand);
	}

	@Override
	public Statement substitue(NameHolder name, Statement statement) {
		return new Mul(
				firsOperand.substitue(name, statement),
				secondOperand.substitue(name, statement));
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
