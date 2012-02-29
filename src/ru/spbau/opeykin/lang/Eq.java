package ru.spbau.opeykin.lang;

public class Eq extends BinaryOperation {

	public Eq(Statement firsOperand, Statement secondOperand) {
		super(firsOperand, secondOperand);
	}

	@Override
	public Statement substitue(NameHolder name, Statement statement) {
		return new Eq(
				firsOperand.substitue(name, statement),
				secondOperand.substitue(name, statement));
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
