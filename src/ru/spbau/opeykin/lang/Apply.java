package ru.spbau.opeykin.lang;


public class Apply implements Statement {
	
	Statement function;
	
	Statement argument;
	
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
                    return fun.nextStatement.substitue(fun.getArgumentName(), y_).evaluate();
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
		Fun fun = function.deFun();
                return "(" + function.getString() + " " + argument.getString() + ")";
	}

	@Override
	public Statement substitue(NameHolder name, Statement statement) {
		return new Apply(
				function.substitue(name, statement),
				argument.substitue(name, statement));
	}

	@Override
	public IntegerConstant deInt() {
		return null;
	}
}
