package ru.spbau.opeykin.lang;

public class Mod extends BinaryOperation {

	public Mod(Statement firsOperand, Statement secondOperand) {
		super(firsOperand, secondOperand);
	}

	@Override
	public Statement substitue(NameHolder name, Statement statement) {
		return new Mod(
				firsOperand.substitue(name, statement),
				secondOperand.substitue(name, statement));
	}

	@Override
	protected String getOperationSymbol() {
		return "%";
	}

	@Override
	protected int evaluateOperation(int first, int second) {
		return first % second;
	}

}
