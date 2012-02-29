
import ru.spbau.opeykin.lang.*;

public class Main {
	
	public static void main(String[] args) {
		
		IntegerConstant one = new IntegerConstant(1);
		IntegerConstant ten = new IntegerConstant(15);
		
		NameHolder funName = new NameHolder("factorial");
		NameHolder x = new NameHolder("x");
		
		Statement s = new IF(x, new Mul(x, new Apply(funName, new Minus(x, one))), one);
		
		Fun fun = new Fun(s, funName, x);
		funName.bind(fun);
		//Apply prog = new Apply(one, ten);
		Apply prog = new Apply(fun, ten);
		
		System.out.println(prog.getString());
		System.out.println(prog.evaluate().getString());
	}
}
