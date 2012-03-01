package ru.spbau.opeykin.lang;

public class Minus extends BinaryOperation {

	public Minus(Statement firsOperand, Statement secondOperand) {
		super(firsOperand, secondOperand);
	}
	
	@Override
	public Statement substitute(NameHolder name, Statement statement) {
		return new Minus(
				firsOperand.substitute(name, statement),
				secondOperand.substitute(name, statement));
	}


	@Override
	protected String getOperationSymbol() {
		return "-";
	}

	@Override
	protected int evaluateOperation(int first, int second) {
		return first - second;
	}

}
