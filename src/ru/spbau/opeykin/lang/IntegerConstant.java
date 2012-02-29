package ru.spbau.opeykin.lang;

public class IntegerConstant implements Statement {
	
	private int value;
	

	public IntegerConstant(int value) {
		super();
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	@Override
	public Statement evaluate() {
		return this;
	}

	@Override
	public String getString() {
		return new Integer(value).toString();
	}

	@Override
	public Statement substitue(NameHolder name, Statement statement) {
		return this;
	}

	@Override
	public IntegerConstant deInt() {
		return this;
	}

	@Override
	public Fun deFun() {
		return null;
	}
}
