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
            // If we applying to nothing
            if(argument == null) {
                return function.evaluate();
            }

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
		//return "APPLY(" + function.getString() + ", " + argument.getString() + ')';
	
		Fun fun = function.deFun();
		
		if (argument != null) {
                    return "(" + function.getString() + " " + argument.getString() + ")";
		} else {
		    return function.getString();
		}

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
