package ru.spbau.opeykin.lang;

public class Fun implements Statement {

    private Statement nextStatement;
    private final NameHolder argumentName;


    public Fun(Statement nextStatement, NameHolder argumentName) {
        super();
        this.nextStatement = nextStatement;
        this.argumentName = argumentName;
    }


    @Override
    public Statement evaluate() {
        nextStatement = nextStatement.evaluate();
        return this;
    }

    @Override
    public String getString() {
        return "{" + argumentName.getString() + " -> " + nextStatement.getString() + "}";
    }

    @Override
    public Statement substitute(NameHolder nameToReplace, Statement statement) {
        if (argumentName.isSame(nameToReplace)) {
            return new Fun(nextStatement, argumentName);
        } else {
            return new Fun(nextStatement.substitute(nameToReplace, statement), argumentName);
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

    public Statement getNextStatement() {
        return nextStatement;
    }
}
