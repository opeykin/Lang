package ru.spbau.opeykin.lang;

public class Div extends BinaryOperation {

	public Div(Statement firsOperand, Statement secondOperand) {
		super(firsOperand, secondOperand);
	}

	@Override
	public Statement substitue(NameHolder name, Statement statement) {
		return new Div(
				firsOperand.substitue(name, statement),
				secondOperand.substitue(name, statement));
	}

	@Override
	protected String getOperationSymbol() {
		return "/";
	}

	@Override
	protected int evaluateOperation(int first, int second) {
		return first / second;
	}

}
