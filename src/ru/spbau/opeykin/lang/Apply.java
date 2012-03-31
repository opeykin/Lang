package ru.spbau.opeykin.lang;


public class Apply implements Statement {
	
	private Statement function;
	private Statement argument;
	
	public Apply(Statement function, Statement argument) {
		super();
		this.function = function;
		this.argument = argument;
	}


	@Override
	public Statement evaluate() {
            function = function.evaluate();
            argument = argument.evaluate();

            Fun fun = function.deFun();

            if (fun != null) {
                function = fun.getNextStatement().substitute(fun.getArgumentName(), argument).evaluate();
                return function;
            } else {
                    return this;
            }
	}

	@Override
	public Fun deFun() {
		return null;
	}

	@Override
	public String getString() {
                return "(" + function.getString() + " " + argument.getString() + ")";
	}

	@Override
	public Statement substitute(NameHolder name, Statement statement) {
		return new Apply(
				function.substitute(name, statement),
				argument.substitute(name, statement));
	}

	@Override
	public IntegerConstant deInt() {
		return null;
	}
}
