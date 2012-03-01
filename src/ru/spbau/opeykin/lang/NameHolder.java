package ru.spbau.opeykin.lang;

import java.util.Map;

public class NameHolder implements Statement {
	
	private final String name;
    private final Map<String, Statement> bindings;
	//private Statement bindedStatement;
	
	
	public NameHolder(String name, Map<String, Statement> bindings) {
		super();
		this.name = name;
        this.bindings = bindings;
	}
	

	@Override
	public Statement evaluate() {
        Statement bindedStatement = bindings.get(name);
		if (bindedStatement == null) {
			return this;
		} else {
			return bindedStatement.evaluate();
		}
	}
	

	public void bind(Statement statement) {
		bindings.put(name, statement);
	}


	@Override
	public String getString() {
		return name;
	}
	
	public boolean isSame(NameHolder holder) {
        return name.compareTo(holder.name) == 0;
	}


	@Override
	public Statement substitute(NameHolder holder, Statement statement) {
		if (this.isSame(holder)) {
			return statement;
		} else {
			return this;
		}
	}


	@Override
	public IntegerConstant deInt() {
        Statement bindedStatement = bindings.get(name);
		if (bindedStatement == null) {
			return null;
		} else {
			return bindedStatement.deInt();
		}
	}


	@Override
	public Fun deFun() {
        Statement bindedStatement = bindings.get(name);
		if (bindedStatement == null) {
			return null;
		} else {
			return bindedStatement.deFun();
		}
	}
}
