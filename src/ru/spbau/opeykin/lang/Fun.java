package ru.spbau.opeykin.lang;

public class Fun implements Statement {
	
	Statement nextStatement;
	NameHolder name;
	NameHolder argumentName;

	public Fun(Statement nextStatement, NameHolder name, NameHolder argumentName) {
		super();
		this.nextStatement = nextStatement;
		this.name = name;
		this.argumentName = argumentName;
	}

	@Override
	public Statement evaluate() {
		return nextStatement.evaluate();
	}

	@Override
	public String getString() {
		return "{" + argumentName.getString() + " -> " + nextStatement.getString() + "}";
	}

	@Override
	public Statement substitue(NameHolder nameToReplace, Statement statement) {
		return new Fun(nextStatement.substitue(nameToReplace, statement), name, argumentName);
	}
	
	@Override
	public IntegerConstant deInt() {
		return null;
	}

	@Override
	public Fun deFun() {
		return this;
	}

	public NameHolder getArgumentName() {
		return argumentName;
	}

	public NameHolder getName() {
		return name;
	}
}
