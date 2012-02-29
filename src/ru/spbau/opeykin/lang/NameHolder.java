package ru.spbau.opeykin.lang;

public class NameHolder implements Statement {
	
	private String name;
	private Statement bindedStatement;
	
	
	public NameHolder(String name) {
		super();
		this.name = name;
	}
	

	@Override
	public Statement evaluate() {
		if (bindedStatement == null) {
			return this;
		} else {
			return bindedStatement.evaluate();
		}
	}
	

	public void bind(Statement statement) {
		bindedStatement = statement;
	}


	@Override
	public String getString() {
		return name;
	}
	
	public boolean isSame(NameHolder holder) {
		if (name.compareTo(holder.name) == 0) {
			return true;
		} else {
			return false;
		}
	}


	@Override
	public Statement substitue(NameHolder holder, Statement statement) {
		if (this.isSame(holder)) {
			return statement;
		} else {
			return this;
		}
	}


	@Override
	public IntegerConstant deInt() {
		if (bindedStatement == null) {
			return null;
		} else {
			return bindedStatement.deInt();
		}
	}


	@Override
	public Fun deFun() {
		if (bindedStatement == null) {
			return null;
		} else {
			return bindedStatement.deFun();
		}
	}
}
