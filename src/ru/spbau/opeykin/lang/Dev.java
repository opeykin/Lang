package ru.spbau.opeykin.lang;

public class Dev extends BinaryOperation {

	public Dev(Statement firsOperand, Statement secondOperand) {
		super(firsOperand, secondOperand);
	}

	@Override
	public Statement substitute(NameHolder name, Statement statement) {
		return new Dev(
				firsOperand.substitute(name, statement),
				secondOperand.substitute(name, statement));
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
