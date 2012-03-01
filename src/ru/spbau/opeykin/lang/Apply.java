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
		Fun fun = function.evaluate().deFun();
		if (fun != null) {
			return fun.substitute(fun.getArgumentName(), argument.evaluate()).evaluate();
		} else {
            return this;
       }

       /*
			Fun fun1 = function.evaluate().deFun();
            if (fun1 != null) {
                return fun1.substitute(fun1.getArgumentName(), argument.evaluate()).evaluate();
            } else {
                return this;
            }
		}
		*/

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
