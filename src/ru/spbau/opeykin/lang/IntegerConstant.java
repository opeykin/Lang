package ru.spbau.opeykin.lang;

public class IntegerConstant implements Statement {
	
	private final int value;
	

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
		return Integer.toString(value);
	}

	@Override
	public Statement substitute(NameHolder name, Statement statement) {
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
