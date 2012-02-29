package ru.spbau.opeykin.lang;

public class IF implements Statement {
	
	Statement condition;
	Statement trueStatement;
	Statement falseStatement;
	
	public IF(Statement condition, Statement trueStatement, Statement falseStatement) {
		super();
		this.condition = condition;
		this.trueStatement = trueStatement;
		this.falseStatement = falseStatement;
	}
	

	@Override
	public Statement evaluate() {
		IntegerConstant result = condition.evaluate().deInt();
		if (result != null) {
			if (result.getValue() != 0) {
				return trueStatement.evaluate();
			} else {
				return falseStatement.evaluate();
			}
		} else {
			return this;
		}
	}



	@Override
	public String getString() {
		return "IF (" + condition.getString() + ") ? " + 
				trueStatement.getString() + " : " + falseStatement.getString(); 
	}

	@Override
	public Statement substitue(NameHolder name, Statement statement) {
		return new IF(
				condition.substitue(name, statement),
				trueStatement.substitue(name, statement),
				falseStatement.substitue(name, statement));
	}

	@Override
	public IntegerConstant deInt() {
		return null;
	}

	@Override
	public Fun deFun() {
		return null;
	}
}
