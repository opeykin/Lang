package ru.spbau.opeykin.lang;

public class Fun implements Statement {
	
	Statement nextStatement;
	NameHolder argumentName;
    boolean argumentSubstituted = false;


    public Fun(Statement nextStatement, NameHolder argumentName) {
        super();
        this.nextStatement = nextStatement;
        this.argumentName = argumentName;
    }


    public Fun(Statement nextStatement, NameHolder argumentName, boolean argumentSubstituted) {
        super();
        this.nextStatement = nextStatement;
        this.argumentName = argumentName;
        this.argumentSubstituted = argumentSubstituted;
    }


	@Override
	public Statement evaluate() {
        if (argumentSubstituted) {
		    return nextStatement.evaluate();
        } else {
            return new Fun(nextStatement.evaluate(), argumentName);
        }
	}

	@Override
	public String getString() {
		return "{" + argumentName.getString() + " -> " + nextStatement.getString() + "}";
	}

	@Override
	public Statement substitue(NameHolder nameToReplace, Statement statement) {
        if (nameToReplace.isSame(argumentName) && !argumentSubstituted) {
            argumentSubstituted = true;
        }
		return new Fun(nextStatement.substitue(nameToReplace, statement), argumentName, argumentSubstituted);
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
}
