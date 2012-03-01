package ru.spbau.opeykin.lang;

public class Fun implements Statement {
	
	Statement nextStatement;
	NameHolder argumentName;


    public Fun(Statement nextStatement, NameHolder argumentName) {
        super();
        this.nextStatement = nextStatement;
        this.argumentName = argumentName;
    }


	@Override
	public Statement evaluate() {
//		return nextStatement.evaluate();
	        return this;
        }

	@Override
	public String getString() {
		return "{" + argumentName.getString() + " -> " + nextStatement.getString() + "}";
	}

	@Override
	public Statement substitue(NameHolder nameToReplace, Statement statement) {
	//	return new Fun(nextStatement.substitue(nameToReplace, statement), name, argumentName);
            if(argumentName.isSame(nameToReplace)) {
                return new Fun(nextStatement, argumentName);
            } else {
		return new Fun(nextStatement.substitue(nameToReplace, statement), argumentName);
            }
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
