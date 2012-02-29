package ru.spbau.opeykin.lang;

public class Dev extends BinaryOperation {

	public Dev(Statement firsOperand, Statement secondOperand) {
		super(firsOperand, secondOperand);
	}

	@Override
	public Statement substitue(NameHolder name, Statement statement) {
		return new Dev(
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
