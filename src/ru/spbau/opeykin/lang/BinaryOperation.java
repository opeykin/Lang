package ru.spbau.opeykin.lang;

public abstract class BinaryOperation implements Statement {
	
	protected Statement firsOperand;
	protected Statement secondOperand;
	

	BinaryOperation(Statement firsOperand, Statement secondOperand) {
		super();
		this.firsOperand = firsOperand;
		this.secondOperand = secondOperand;
	}

	@Override
	public Statement evaluate() {
        firsOperand = firsOperand.evaluate();
        secondOperand = secondOperand.evaluate();
		IntegerConstant first = firsOperand.deInt();
		IntegerConstant second = secondOperand.deInt();
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
