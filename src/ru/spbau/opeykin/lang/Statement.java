package ru.spbau.opeykin.lang;

public interface Statement {
	public Statement evaluate();
	public String getString();
	public Statement substitute(NameHolder name, Statement statement);
	public IntegerConstant deInt();
	public Fun deFun();
}
