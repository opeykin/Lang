package ru.spbau.opeykin.lang;

public class IF implements Statement {
	
	private final Statement condition;
	private final Statement trueStatement;
	private final Statement falseStatement;
	
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
        return
                condition.getString() + " ? " + trueStatement.getString() +
                " : " + falseStatement.getString();
	}

	@Override
	public Statement substitute(NameHolder name, Statement statement) {
		return new IF(
				condition.substitute(name, statement),
				trueStatement.substitute(name, statement),
				falseStatement.substitute(name, statement));
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
