package ru.spbau.opeykin.lang;


public class Apply implements Statement {
	
	private Statement function;
	private Statement argument;
	
	public Apply(Statement function, Statement argument) {
		super();
		this.function = function;
		this.argument = argument;
	}

	public Apply() {
		super();
	}

	public Statement getFunction() {
		return function;
	}

	public void setArgument(Statement argument) {
		this.argument = argument;
	}


	@Override
	public Statement evaluate() {
            Statement x_ = function.evaluate();
            Statement y_ = argument.evaluate();

            Fun fun = x_.deFun();

            if (fun != null) {
                    return fun.getNextStatement().substitute(fun.getArgumentName(), y_).evaluate();
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
