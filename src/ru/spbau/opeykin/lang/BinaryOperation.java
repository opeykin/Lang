package ru.spbau.opeykin.lang;

abstract class BinaryOperation implements Statement {
	
	final Statement firsOperand;
	final Statement secondOperand;
	

	BinaryOperation(Statement firsOperand, Statement secondOperand) {
		super();
		this.firsOperand = firsOperand;
		this.secondOperand = secondOperand;
	}

	@Override
	public Statement evaluate() {
		IntegerConstant first = firsOperand.evaluate().deInt();
		IntegerConstant second = secondOperand.evaluate().deInt();
		if (first != null && second != null) {
			return new IntegerConstant(
					evaluateOperation(first.getValue(),	second.getValue()));
		} else {
			return this;
		}
	}

	
	@Override
	public IntegerConstant deInt() {
		return null;
	}
	
/*
	@Override
	public Statement substitute(NameHolder holder, Statement statement) {
		firsOperand = firsOperand.substitute(holder, statement);
		secondOperand = secondOperand.substitute(holder, statement);
		return this;
	}
*/
	
	public String getString() {
		return '(' + firsOperand.getString() + ' ' + 
				getOperationSymbol() + ' ' + secondOperand.getString() + ')';
	}
	
	public Fun deFun() {
		return null;
	}
	
	
	protected abstract String getOperationSymbol();
	
	
	protected abstract int evaluateOperation(int first, int second);
}
